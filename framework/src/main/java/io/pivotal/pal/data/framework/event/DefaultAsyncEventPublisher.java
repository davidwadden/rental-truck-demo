package io.pivotal.pal.data.framework.event;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class DefaultAsyncEventPublisher<T> extends AsyncEventChannel implements AsyncEventPublisher<T> {

    public DefaultAsyncEventPublisher(String eventName) {
        super(eventName);
    }

    public void publish(T data) {
        Set<BlockingQueue<?>> queues = super.getQueues();

        for (BlockingQueue<?> queue : queues) {
            //noinspection unchecked
            ((BlockingQueue<T>) queue).offer(data);
        }
    }
}
