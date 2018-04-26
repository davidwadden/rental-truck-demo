package io.pivotal.pal.data.framework.event;

public class SyncEventSubscriberAdapter<C, R> extends SyncEventChannel {

    private SyncEventHandler<C, R> handler;
    private SyncEventHandler<C, R> errorHandler;

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler) {
        this(eventName, handler, null);
    }

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> handler, SyncEventHandler<C, R> errorHandler) {
        super(eventName);
        this.handler = handler;
        this.errorHandler = errorHandler;

        //noinspection unchecked
        registerSubscriber((SyncEventSubscriberAdapter<Object, Object>) this);
    }

    public R onEvent(C data) {
        try {
            return handler.onEvent(data);
        } catch (Exception e) {
            if (errorHandler == null) {
                return null;
            } else {
                return errorHandler.onEvent(data);
            }
        }
    }
}
