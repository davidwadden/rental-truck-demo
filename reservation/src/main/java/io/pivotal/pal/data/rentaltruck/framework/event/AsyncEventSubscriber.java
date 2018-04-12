package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AsyncEventSubscriber<T> extends AsyncEventChannel {

    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public AsyncEventSubscriber(String eventName) {
        super(eventName);
        addQueue(queue);
        new Processor().start();
    }

    abstract protected void onEvent(T data);

    private class Processor extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    T data = queue.take();
                    onEvent(data);
                } catch (Exception x) {
                    // TODO
                }
            }
        }
    }
}
