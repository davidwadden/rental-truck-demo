package io.pivotal.pal.data.framework.event;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncEventTest {

    private static final String EVENT_NAME = "test";

    @Test
    public void success() {
        DefaultSyncEventPublisher<String, String> publisher = new DefaultSyncEventPublisher<>(EVENT_NAME);
        Handler handler = new Handler();
        SyncEventSubscriberAdapter<String, String> subscriber = new SyncEventSubscriberAdapter<>(EVENT_NAME, handler);

        handler.setData(null);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void error_withHandler() {
        DefaultSyncEventPublisher<String, String> publisher = new DefaultSyncEventPublisher<>(EVENT_NAME);
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler();
        Handler errorHandler = new Handler();
        SyncEventSubscriberAdapter<String, String> subscriber = new SyncEventSubscriberAdapter<>(EVENT_NAME, handler, errorHandler);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(errorHandler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void error_withoutHandler() {
        DefaultSyncEventPublisher<String, String> publisher = new DefaultSyncEventPublisher<>(EVENT_NAME);
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler();
        SyncEventSubscriberAdapter<String, String> subscriber = new SyncEventSubscriberAdapter<>(EVENT_NAME, handler);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(result).isNull();
    }

    private class ExceptionThrowingHandler implements SyncEventHandler<String, String> {

        @Override
        public String onEvent(String data) {
            throw new IllegalArgumentException("data = "+data);
        }
    }

    private class Handler implements SyncEventHandler<String, String> {

        private String data;

        public Handler() {
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String onEvent(String data) {
            this.data = data;
            return data;
        }
    }
}
