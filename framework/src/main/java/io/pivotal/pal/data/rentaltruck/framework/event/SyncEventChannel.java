package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class SyncEventChannel {

    private static Map<String, Set<SyncEventSubscriberAdapter<Object, Object>>> subscribers = new ConcurrentHashMap<>();

    private String eventName;

    protected SyncEventChannel(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    protected Set<SyncEventSubscriberAdapter<Object, Object>> getSubscribers() {
        Set<SyncEventSubscriberAdapter<Object, Object>> set = subscribers.get(eventName);

        if (set == null) {
            throw new IllegalArgumentException("Subscriber for event " + eventName + " not found");
        }

        return set;
    }

    protected void registerSubscriber(SyncEventSubscriberAdapter<Object, Object> subscriber) {
        Set<SyncEventSubscriberAdapter<Object, Object>> set = subscribers.computeIfAbsent(eventName, k -> new CopyOnWriteArraySet<>());
        set.add(subscriber);
    }
}
