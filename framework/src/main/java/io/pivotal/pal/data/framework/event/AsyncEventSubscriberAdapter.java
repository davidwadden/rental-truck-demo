package io.pivotal.pal.data.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncEventSubscriberAdapter<T> extends AsyncEventChannel {

    private static final Logger logger = LoggerFactory.getLogger(AsyncEventSubscriberAdapter.class);

    private AsyncEventHandler<T> handler;
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> subscriber) {
        super(eventName);
        this.handler = subscriber;

        super.addQueue(queue);
        new Processor().start();
    }

    private class Processor extends Thread {

        private Processor() {}

        @Override
        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    T data = queue.take();

                    logger.debug("calling event handler={}: data={}");
                    handler.onEvent(data);
                } catch (Exception x) {
                    logger.error("exception thrown in event processor thread", x);
                }
            }
        }
    }
}
