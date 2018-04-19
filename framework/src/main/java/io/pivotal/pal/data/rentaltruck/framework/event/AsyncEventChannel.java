package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class AsyncEventChannel {

    private static Map<String, Set<BlockingQueue<?>>> queues = new ConcurrentHashMap<>();

    private String eventName;

    public AsyncEventChannel(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    protected Set<BlockingQueue<?>> getQueues() {
        return queues.computeIfAbsent(eventName, k -> new ConcurrentSkipListSet<>());
    }

    protected void addQueue(BlockingQueue<?> queue) {
        Set<BlockingQueue<?>> set = queues.computeIfAbsent(eventName, k -> new ConcurrentSkipListSet<>());
        set.add(queue);
    }
}
