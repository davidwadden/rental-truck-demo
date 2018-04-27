package io.pivotal.pal.data.framework.event;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SyncEventTest {

    private static final String EVENT_NAME = "test";

    private DefaultSyncEventPublisher<String, String> publisher;

    @Before
    public void setUp() {
        publisher = new DefaultSyncEventPublisher<>(EVENT_NAME);
    }

    @Test
    public void error_missingEventName() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SyncEventSubscriberAdapter<>(null, new Handler()));
    }

    @Test
    public void error_missingHandler() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new AsyncEventSubscriberAdapter<>(EVENT_NAME, null));
    }

    @Test
    public void error_invalidMaxRetryCount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SyncEventSubscriberAdapter<>(EVENT_NAME,
                        new ExceptionThrowingHandler(1),
                        null, -1, 100, 2, null));
    }

    @Test
    public void error_invalidInitialRetryWaitTime() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SyncEventSubscriberAdapter<>(EVENT_NAME,
                        new ExceptionThrowingHandler(1),
                        null, 1, 10, 2, null));
    }

    @Test
    public void error_invalidRetryWaitTimeMultiplier() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SyncEventSubscriberAdapter<>(EVENT_NAME,
                        new ExceptionThrowingHandler(1),
                        null, 1, 100, 0, null));
    }

    @Test
    public void success_withoutRetry() {
        Handler handler = new Handler();
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber = new SyncEventSubscriberAdapter<>(EVENT_NAME, handler);

        handler.setData(null);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void success_withRetry() {
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler(1);
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber =
                new SyncEventSubscriberAdapter<>(EVENT_NAME, handler, null, 1, 100, 2, null);

        handler.setData(null);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void success_withRetryWithRecoverableExceptions() {
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler(1);
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber =
                new SyncEventSubscriberAdapter<>(EVENT_NAME, handler, null, 1, 100, 2, new HashSet<>(Collections.singletonList(IllegalArgumentException.class)));

        handler.setData(null);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void error_withRetryWithNonRecoverableExceptions() {
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler(1);
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber =
                new SyncEventSubscriberAdapter<>(EVENT_NAME, handler, null, 1, 100, 2, new HashSet<>(Collections.singletonList(IllegalStateException.class)));

        handler.setData(null);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isNull();
        assertThat(result).isNull();
    }

    @Test
    public void error_withHandlerNoRetry() {
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler(1);
        Handler errorHandler = new Handler();
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber =
                new SyncEventSubscriberAdapter<>(EVENT_NAME, handler, errorHandler);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(errorHandler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    @Test
    public void error_withoutHandlerNoRetry() {
        ExceptionThrowingHandler handler = new ExceptionThrowingHandler(1);
        //noinspection unused
        SyncEventSubscriberAdapter<String, String> subscriber =
                new SyncEventSubscriberAdapter<>(EVENT_NAME, handler);

        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(result).isNull();
    }

    @SuppressWarnings("WeakerAccess")
    private class ExceptionThrowingHandler implements SyncEventHandler<String, String> {

        int maxCount;
        int count = 0;
        String data;

        public ExceptionThrowingHandler(int maxCount) {
            this.maxCount = maxCount;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String onEvent(String data) {
            if (count++ < maxCount) {
                throw new IllegalArgumentException("data = " + data);
            } else {
                this.data = data;
                return data;
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
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
