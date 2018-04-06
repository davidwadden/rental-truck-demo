package io.pivotal.pal.data.rentaltruck.framework.event;

public abstract class SyncEventPublisher<C, R> extends SyncEventChannel {

    private String eventName;

    public SyncEventPublisher(String eventName) {
        this.eventName = eventName;
    }

    protected R publish(C data) {
        SyncEventSubscriber<C, R> subscriber = getSubscriber(eventName);
        return subscriber.onEvent(data);
    }
}
