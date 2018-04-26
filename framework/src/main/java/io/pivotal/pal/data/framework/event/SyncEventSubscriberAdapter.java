package io.pivotal.pal.data.framework.event;

public class SyncEventSubscriberAdapter<C, R> extends SyncEventChannel {

    private SyncEventHandler<C, R> handler;
    private SyncEventHandler<C, R> errorHandler;
    private int maxRetryCount;
    private long initialRetryWaitTime;
    private int retryWaitTimeMultiplier;

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler, SyncEventHandler<C, R> errorHandler,
                                      int maxRetryCount, long initialRetryWaitTime, int retryWaitTimeMultiplier) {
        super(eventName);
        this.handler = handler;
        this.errorHandler = errorHandler;
        this.maxRetryCount = maxRetryCount;
        this.initialRetryWaitTime = initialRetryWaitTime;
        this.retryWaitTimeMultiplier = retryWaitTimeMultiplier;

        //noinspection unchecked
        registerSubscriber((SyncEventSubscriberAdapter<Object, Object>) this);
    }

    public R onEvent(C data) {
        try {
            long waitTime = initialRetryWaitTime;

            for (int i = 0; i <= maxRetryCount; ++i) {
                try {
                    return handler.onEvent(data);
                } catch (Exception t) {
                    if (i < maxRetryCount) {
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
