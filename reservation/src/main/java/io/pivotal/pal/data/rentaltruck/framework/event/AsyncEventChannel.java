package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class AsyncEventChannel {

    private String eventName;
    private static Map<String, Set<BlockingQueue<?>>> queues = new ConcurrentHashMap<>();

    public AsyncEventChannel(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    protected void addQueue(BlockingQueue<?> queue) {
        Set<BlockingQueue<?>> set = queues.computeIfAbsent(eventName, k -> new ConcurrentSkipListSet<>());
        set.add(queue);
    }

    protected Set<BlockingQueue<?>> getQueues() {
        return queues.computeIfAbsent(eventName, k -> new ConcurrentSkipListSet<>());
    }
}
