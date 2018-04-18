package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class SyncEventChannel {

    private String eventName;
    private static Map<String, Set<SyncEventSubscriberAdapter<Object,Object>>> subscribers = new ConcurrentHashMap<>();

    public String getEventName() {
        return eventName;
    }

    protected void registerSubscriber(SyncEventSubscriberAdapter<Object,Object> subscriber) {
        Set<SyncEventSubscriberAdapter<Object,Object>> set = subscribers.computeIfAbsent(eventName, k-> new ConcurrentSkipListSet<>());
        set.add(subscriber);
    }

    protected Set<SyncEventSubscriberAdapter<Object,Object>> getSubscribers() {
        Set<SyncEventSubscriberAdapter<Object,Object>> set = subscribers.get(eventName);

        if (set == null) {
            throw new IllegalArgumentException("Subscriber for event "+eventName+" not found");
        }

        return set;
    }

    protected SyncEventChannel(String eventName) {
        this.eventName = eventName;
    }
}
