package io.pivotal.pal.data.rentaltruck.framework.event;

public abstract class SyncEventSubscriber<C, R> extends SyncEventChannel {

    public SyncEventSubscriber(String eventName) {
        registerSubscriber(eventName, this);
    }

    protected abstract R onEvent(C data);
}
