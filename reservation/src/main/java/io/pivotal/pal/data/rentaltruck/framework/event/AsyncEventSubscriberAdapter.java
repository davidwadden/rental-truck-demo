package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncEventSubscriberAdapter<T> extends AsyncEventChannel {

    private AsyncEventHandler<T> handler;
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> subscriber) {
        super(eventName);
        this.handler = subscriber;
        addQueue(queue);
        new Processor().start();
    }

    private class Processor extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    T data = queue.take();
                    handler.onEvent(data);
                } catch (Exception x) {
                    // TODO
                }
            }
        }
    }
}
