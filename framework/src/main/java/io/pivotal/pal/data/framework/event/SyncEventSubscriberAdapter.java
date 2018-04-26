package io.pivotal.pal.data.framework.event;

import org.springframework.util.Assert;

import java.util.Set;

public class SyncEventSubscriberAdapter<C, R> extends SyncEventChannel {

    private SyncEventHandler<C, R> handler;
    private SyncEventHandler<C, R> errorHandler;
    private int maxRetryCount;
    private long initialRetryWaitTime;
    private int retryWaitTimeMultiplier;
    private Set<Class<?>> recoverableExceptions;

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler) {
        this(eventName, handler, null, 0,0,0,null);
    }

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler, SyncEventHandler<C, R> errorHandler) {
        this(eventName, handler, errorHandler, 0,0,0,null);
    }

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler, SyncEventHandler<C, R> errorHandler,
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

        //noinspection unchecked
        registerSubscriber((SyncEventSubscriberAdapter<Object, Object>) this);
    }

    public R onEvent(C data) {
        try {
            long waitTime = initialRetryWaitTime;

            for (int retryCount = 0; retryCount <= maxRetryCount; ++retryCount) {
                try {
                    return handler.onEvent(data);
                } catch (Exception t) {
                    if (recoverableExceptions != null && !recoverableExceptions.isEmpty() && !recoverableExceptions.contains(t.getClass())) {
                        throw t;
                    }

                    if (retryCount < maxRetryCount) {
                        synchronized (this) {
                            this.wait(waitTime);
                        }
                        waitTime *= retryWaitTimeMultiplier;
                    } else {
                        throw t;
                    }
                }
            }

            return null;
        } catch (Exception e) {
            if (errorHandler == null) {
                return null;
            } else {
                return errorHandler.onEvent(data);
            }
        }
    }
}
