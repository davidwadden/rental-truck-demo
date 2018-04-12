package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public abstract class AsyncEventChannel {

    private String eventName;
    private static Map<String, Set<BlockingQueue<?>>> queues = new HashMap<>();

    public AsyncEventChannel(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    protected void addQueue(BlockingQueue<?> queue) {
        Set<BlockingQueue<?>> set = queues.computeIfAbsent(eventName, k -> new HashSet<>());
        set.add(queue);
    }

    protected Set<BlockingQueue<?>> getQueues() {
        return queues.computeIfAbsent(eventName, k -> new HashSet<>());
    }
}
