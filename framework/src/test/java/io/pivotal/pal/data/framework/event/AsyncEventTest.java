package io.pivotal.pal.data.framework.event;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

public class AsyncEventTest {

    private static final String EVENT_NAME = "test";

    private String data;
    private String errorData;

    @Before
    public void setUp() {
        data = null;
        errorData = null;
    }

    @Test
    public void success() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME, new Handler());
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> assertThat(data).isEqualTo(someData));
    }

    @Test
    public void error_withHandlerNoRetry() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(1),
                new ErrorHandler());
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> {
                    assertThat(errorData).isEqualTo(someData);
                    assertThat(data).isNull();
                });
    }

    @Test
    public void success_withoutHandlerWithRetry() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(1),
                null, 1, 100, 2, null);
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(TWO_SECONDS)
                .untilAsserted(() -> {
                    assertThat(errorData).isNull();
                    assertThat(data).isEqualTo(someData);
                });
    }

    @Test
    public void success_withoutHandlerWithRetryWithRecoverableExceptions() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(1), null, 1, 100,
                2, new HashSet<>(Arrays.asList(IllegalArgumentException.class)));
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(TWO_SECONDS)
                .untilAsserted(() -> {
                    assertThat(errorData).isNull();
                    assertThat(data).isEqualTo(someData);
                });
    }

    @Test
    public void error_withoutHandlerWithRetryWithNonRecoverableExceptions() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(1), null, 1, 100,
                2, new HashSet<>(Arrays.asList(IllegalStateException.class)));
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(TWO_SECONDS)
                .untilAsserted(() -> {
                    assertThat(errorData).isNull();
                    assertThat(data).isNull();
                });
    }

    @Test
    public void success_withoutHandlerWithRetryExceeded() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(2),
                null, 1, 100, 2, null);
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(TWO_SECONDS)
                .untilAsserted(() -> {
                    assertThat(errorData).isNull();
                    assertThat(data).isNull();
                });
    }

    @Test
    public void error_withoutHandlerNoRetry() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(1), null, 0, 0, 0, null);
        subscriber.start();

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> {
                    assertThat(errorData).isNull();
                    assertThat(data).isNull();
                });
    }

    private class Handler implements AsyncEventHandler<String> {

        @Override
        public void onEvent(String data) {
            AsyncEventTest.this.data = data;
        }
    }

    private class ErrorHandler implements AsyncEventHandler<String> {

        @Override
        public void onEvent(String data) {
            AsyncEventTest.this.errorData = data;
        }
    }

    private class ExceptionThrowingHandler implements AsyncEventHandler<String> {

        int maxCount;
        int count = 0;

        public ExceptionThrowingHandler(int maxCount) {
            this.maxCount = maxCount;
        }

        @Override
        public void onEvent(String data) {
            if (count++ < maxCount) {
                throw new IllegalArgumentException("data = " + data);
            } else {
                AsyncEventTest.this.data = data;
            }
        }
    }
}
