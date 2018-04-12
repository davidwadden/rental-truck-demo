package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

public abstract class AsyncEventPublisher<T> extends AsyncEventChannel {

    public AsyncEventPublisher(String eventName) {
        super(eventName);
    }

    public void publish(T data) {
        Set<BlockingQueue<?>> queues = getQueues();
        for (BlockingQueue<?> queue: queues) {
            ((BlockingQueue<T>)queue).offer(data);
        }
    }
}
