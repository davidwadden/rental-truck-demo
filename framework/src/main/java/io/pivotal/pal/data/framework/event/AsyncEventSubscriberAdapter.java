package io.pivotal.pal.data.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncEventSubscriberAdapter<T> extends AsyncEventChannel implements SmartLifecycle {

    private static final Logger logger = LoggerFactory.getLogger(AsyncEventSubscriberAdapter.class);

    private AsyncEventHandler<T> handler;
    private AsyncEventHandler<T> errorHandler;
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    private int maxRetryCount;
    private long initialRetryWaitTime;
    private int retryWaitTimeMultiplier;
    private Set<Class<?>> recoverableExceptions;

    private boolean running = false;

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler) {
        this(eventName, handler, null, 0, 0, 0, null);
    }

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler, AsyncEventHandler<T> errorHandler) {
        this(eventName, handler, errorHandler, 0, 0, 0, null);
    }

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler, AsyncEventHandler<T> errorHandler,
                                       int maxRetryCount, long initialRetryWaitTime, int retryWaitTimeMultiplier,
                                       Set<Class<?>> recoverableExceptions) {
        super(eventName);

        Assert.hasText(eventName, "Event name must be specified");
        Assert.notNull(handler, "Handler must be specified");

        Assert.isTrue(maxRetryCount >= 0, "Invalid maxRetryCount, must be >=0: " + maxRetryCount);

        if (maxRetryCount > 0) {
            Assert.isTrue(initialRetryWaitTime >= 100, "Invalid initialRetryWaitTime, must be >=100: " + initialRetryWaitTime);
            Assert.isTrue(retryWaitTimeMultiplier >= 1, "Invalid retryWaitTimeMultiplier, must be >=1: " + retryWaitTimeMultiplier);
        }

        this.handler = handler;
        this.errorHandler = errorHandler;
        this.maxRetryCount = maxRetryCount;
        this.initialRetryWaitTime = initialRetryWaitTime;
        this.retryWaitTimeMultiplier = retryWaitTimeMultiplier;
        this.recoverableExceptions = recoverableExceptions;

        super.addQueue(queue);
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        running = false;
    }

    @Override
    public void start() {
        running = true;
        new Processor().start();
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    private void processEntry() {
        T data = null;

        try {
            data = queue.take();
            long waitTime = initialRetryWaitTime;

            for (int retryCount = 0; retryCount <= maxRetryCount; ++retryCount) {
                logger.debug("calling event handler={}: data={}", handler, data);

                try {
                    handler.onEvent(data);
                    break;
                } catch (Exception e) {
                    waitTime = handleException(e, waitTime, retryCount);
                }
            }
        } catch (Exception x) {
            logger.error("exception thrown in event processor thread: x={}, data={}", x.toString(), data, x);

            if (errorHandler != null && data != null) {
                errorHandler.onEvent(data);
            }
        }
    }

    private long handleException(Exception e, long waitTime, int retryCount) throws Exception {
        if (recoverableExceptions != null &&
                !recoverableExceptions.isEmpty() &&
                !recoverableExceptions.contains(e.getClass())) {
            // if recoverable exceptions specified and this exception is not recoverable, rethrow
            throw e;
        }

        if (retryCount < maxRetryCount) {
            try {
                synchronized (this) {
                    this.wait(waitTime);
                }

                waitTime *= retryWaitTimeMultiplier;
            } catch (InterruptedException t) {
                // no-op
            }

            return waitTime;
        } else {
            throw e;
        }
    }

    private class Processor extends Thread {

        private Processor() {
        }

        @Override
        public void run() {
            while (running) {
                processEntry();
            }
        }
    }
}
