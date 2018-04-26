package io.pivotal.pal.data.framework.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncEventSubscriberAdapter<T> extends AsyncEventChannel {

    private static final Logger logger = LoggerFactory.getLogger(AsyncEventSubscriberAdapter.class);

    private AsyncEventHandler<T> handler;
    private AsyncEventHandler<T> errorHandler;
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler) {
        this(eventName, handler, null);
    }

    public AsyncEventSubscriberAdapter(String eventName, AsyncEventHandler<T> handler, AsyncEventHandler<T> errorHandler) {
        super(eventName);
        this.handler = handler;
        this.errorHandler = errorHandler;

        super.addQueue(queue);
        new Processor().start();
    }

    private class Processor extends Thread {

        private Processor() {}

        @Override
        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                T data = null;

                try {
                    data = queue.take();

                    logger.debug("calling event handler={}: data={}");
                    handler.onEvent(data);
                } catch (Exception x) {
                    logger.error("exception thrown in event processor thread", x);

                    if (errorHandler != null && data != null) {
                        errorHandler.onEvent(data);
                    }
                }
            }
        }
    }
}
