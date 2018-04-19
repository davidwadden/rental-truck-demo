package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class DefaultAsyncEventPublisher<T> extends AsyncEventChannel implements AsyncEventPublisher<T> {

    public DefaultAsyncEventPublisher(String eventName) {
        super(eventName);
    }

    public void publish(T data) {
        Set<BlockingQueue<?>> queues = getQueues();
        for (BlockingQueue<?> queue : queues) {
            ((BlockingQueue<T>) queue).offer(data);
        }
    }
}
