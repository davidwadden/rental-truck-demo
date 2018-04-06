package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.concurrent.BlockingQueue;

public abstract class AsyncEventPublisher<T> extends AsyncEventChannel {

    private BlockingQueue<T> queue;

    public AsyncEventPublisher(String eventName) {
        queue = getQueue(eventName);
    }

    public void publish(T data) {
        queue.offer(data);
    }
}
