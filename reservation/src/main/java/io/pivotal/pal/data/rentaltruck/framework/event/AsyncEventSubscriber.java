package io.pivotal.pal.data.rentaltruck.framework.event;

import java.util.concurrent.BlockingQueue;

public abstract class AsyncEventSubscriber<T> extends AsyncEventChannel {

    private BlockingQueue<T> queue;
    private String eventName;

    public AsyncEventSubscriber(String eventName) {
        this.eventName = eventName;
        this.queue = getQueue(eventName);
        new Processor().start();
    }

    public String getEventName() {
        return eventName;
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

                }
            }
        }
    }
}
