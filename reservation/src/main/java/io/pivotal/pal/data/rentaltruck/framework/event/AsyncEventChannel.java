package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AsyncEventChannel {

    private static Map<String, BlockingQueue<?>> queues = new HashMap<>();

    protected AsyncEventChannel() {
    }

    protected static <T> BlockingQueue<T> getQueue(String eventName) {
        return (BlockingQueue<T>) queues.computeIfAbsent(eventName, k -> new LinkedBlockingQueue<>());
    }
}
