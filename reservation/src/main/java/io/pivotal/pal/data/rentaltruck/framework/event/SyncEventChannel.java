package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class SyncEventChannel {

    private String eventName;
    private static Map<String, Set<SyncEventSubscriber<Object,Object>>> subscribers = new HashMap<>();

    public String getEventName() {
        return eventName;
    }

    protected void registerSubscriber(SyncEventSubscriber<Object,Object> subscriber) {
        Set<SyncEventSubscriber<Object,Object>> set = subscribers.computeIfAbsent(eventName, k-> new HashSet<>());
        set.add(subscriber);
    }

    protected Set<SyncEventSubscriber<Object,Object>> getSubscribers() {
        Set<SyncEventSubscriber<Object,Object>> set = subscribers.get(eventName);

        if (set == null) {
            throw new IllegalArgumentException("Subscriber for event "+eventName+" not found");
        }

        return set;
    }

    protected SyncEventChannel(String eventName) {
        this.eventName = eventName;
    }
}
