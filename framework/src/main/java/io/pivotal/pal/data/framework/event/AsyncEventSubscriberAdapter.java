package io.pivotal.pal.data.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncEventSubscriberAdapter<T> extends AsyncEventChannel {

    private static final Logger logger = LoggerFactory.getLogger(AsyncEventSubscriberAdapter.class);

    private AsyncEventHandler<T> handler;
    private AsyncEventHandler<T> errorHandler;
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    private int maxRetryCount;
    private long initialRetryWaitTime;
    private int retryWaitTimeMultiplier;

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler, AsyncEventHandler<T> errorHandler,
                                       int maxRetryCount, long initialRetryWaitTime, int retryWaitTimeMultiplier) {
        super(eventName);

        Assert.isTrue(maxRetryCount >= 0, "Invalid maxRetryCount: " + maxRetryCount);

        this.handler = handler;
        this.errorHandler = errorHandler;
        this.maxRetryCount = maxRetryCount;
        this.initialRetryWaitTime = initialRetryWaitTime;
        this.retryWaitTimeMultiplier = retryWaitTimeMultiplier;

        super.addQueue(queue);
        new Processor().start();
    }

    private class Processor extends Thread {

        private Processor() {
        }

        @Override
        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                T data = null;

                try {
                    data = queue.take();
                    long waitTime = initialRetryWaitTime;

                    for (int i = 0; i <= maxRetryCount; ++i) {
                        logger.debug("calling event handler={}: data={}", handler, data);

                        try {
                            handler.onEvent(data);
                            break;
                        } catch (Exception e) {
                            if (i < maxRetryCount) {
                                try {
                                    synchronized (this) {
                                        this.wait(waitTime);
                                    }

                                    waitTime *= retryWaitTimeMultiplier;
                                } catch (InterruptedException t) {
                                    // no-op
                                }
                            } else {
                                throw e;
                            }
                        }
                    }
                } catch (Exception x) {
                    logger.error("exception thrown in event processor thread: x={}, data={}", x.toString(), data, x);

                    if (errorHandler != null && data != null) {
                        errorHandler.onEvent(data);
                    }
                }
            }
        }
    }
}
