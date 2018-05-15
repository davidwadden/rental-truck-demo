package io.pivotal.pal.data.framework.event;

import java.util.Set;

public class DefaultSyncEventPublisher<C, R> extends SyncEventChannel implements SyncEventPublisher<C, R> {

    public DefaultSyncEventPublisher(String eventName) {
        super(eventName);
    }

    public R publish(C event) {
        Set<SyncEventSubscriberAdapter<Object, Object>> set = getSubscribers();
        R retValue = null;

        for (SyncEventSubscriberAdapter<Object, Object> subscriber : set) {
            //noinspection unchecked
            retValue = (R) subscriber.onEvent(event);
        }

        return retValue;
    }
}
