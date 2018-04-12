package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Set;

public abstract class SyncEventPublisher<C, R> extends SyncEventChannel {

    public SyncEventPublisher(String eventName) {
        super(eventName);
    }

    protected R publish(C data) {
        Set<SyncEventSubscriber<Object, Object>> set = getSubscribers();
        R retValue = null;

        for (SyncEventSubscriber<Object, Object> subscriber : set) {
            retValue = (R) subscriber.onEvent(data);
        }

        return retValue;
    }
}
