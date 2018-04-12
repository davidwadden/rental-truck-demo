package io.pivotal.pal.data.rentaltruck.framework.event;

public abstract class SyncEventSubscriber<C, R> extends SyncEventChannel {

    public SyncEventSubscriber(String eventName) {
        super(eventName);
        registerSubscriber((SyncEventSubscriber<Object, Object>) this);
    }

    protected abstract R onEvent(C data);
}
