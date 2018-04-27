package io.pivotal.pal.data.framework.event;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

public class AsyncEventTest {

    private static final String EVENT_NAME = "test";

    private String data;
    private String errorData;
    private DefaultAsyncEventPublisher<String> publisher;

    @Before
    public void setUp() {
        data = null;
        errorData = null;
        publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
    }

    @Test
    public void error_missingEventName() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new AsyncEventSubscriberAdapter<>(null, new Handler()));
    }

    @Test
    public void error_missingHandler() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new AsyncEventSubscriberAdapter<>(EVENT_NAME, null));
    }

    @Test
    public void error_invalidMaxRetryCount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new AsyncEventSubscriberAdapter<>(
                                EVENT_NAME,
                                new ExceptionThrowingHandler(1),
                                null,
                                -1,
                                100,
                                2,
                                null
                        )
                );
    }

    @Test
    public void error_invalidInitialRetryWaitTime() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new AsyncEventSubscriberAdapter<>(
                                EVENT_NAME,
                                new ExceptionThrowingHandler(1),
                                null,
                                1,
                                10,
                                2,
                                null
                        )
                );
    }

    @Test
    public void error_invalidRetryWaitTimeMultiplier() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new AsyncEventSubscriberAdapter<>(
                                EVENT_NAME,
                                new ExceptionThrowingHandler(1),
                                null,
                                1,
                                100,
                                0, null
                        )
                );
    }

    @Test
    public void success() {
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(1),
                new ErrorHandler()
        );
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(1),
                null,
                1,
                100,
                2,
                null
        );
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(1),
                null,
                1,
                100,
                2,
                new HashSet<>(Collections.singletonList(IllegalArgumentException.class))
        );
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(1),
                null,
                1,
                100,
                2,
                new HashSet<>(Collections.singletonList(IllegalStateException.class))
        );
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(2),
                null,
                1,
                100,
                2,
                null
        );
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
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(
                EVENT_NAME,
                new ExceptionThrowingHandler(1),
                null,
                0,
                0,
                0,
                null
        );
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

    @SuppressWarnings("WeakerAccess")
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
            }

            AsyncEventTest.this.data = data;
        }
    }
}
