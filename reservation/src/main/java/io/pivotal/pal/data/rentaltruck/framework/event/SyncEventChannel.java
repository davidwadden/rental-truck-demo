package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.HashMap;
import java.util.Map;

public abstract class SyncEventChannel {

    private static Map<String, SyncEventSubscriber<?>> subscribers = new HashMap<>();

    protected static <T> void registerSubscriber(String eventName, SyncEventSubscriber<T> subscriber) {
        if (subscribers.putIfAbsent(eventName, subscriber) != null) {
            throw new IllegalArgumentException("Subscriber for event "+eventName+" already registered");
        }
    }

    protected static <T> SyncEventSubscriber<T> getSubscriber(String eventName) {
        SyncEventSubscriber<T> subscriber = (SyncEventSubscriber<T>) subscribers.get(eventName);

        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber for event "+eventName+" not found");
        }

        return subscriber;
    }

    protected SyncEventChannel() {
    }
}
