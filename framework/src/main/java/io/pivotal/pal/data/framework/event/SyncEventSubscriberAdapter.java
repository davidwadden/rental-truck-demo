package io.pivotal.pal.data.framework.event;

public class SyncEventSubscriberAdapter<C, R> extends SyncEventChannel {

    private SyncEventHandler<C, R> handler;

    public SyncEventSubscriberAdapter(String eventName, SyncEventHandler<C, R> subscriber) {
        super(eventName);
        this.handler = subscriber;

        //noinspection unchecked
        registerSubscriber((SyncEventSubscriberAdapter<Object, Object>) this);
    }

    public R onEvent(C data) {
        return handler.onEvent(data);
    }
}
