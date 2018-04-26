package io.pivotal.pal.data.framework.event;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

public class AsyncEventTest {

    private static final String EVENT_NAME = "test";

    private String data;
    private String errorData;

    @Before
    public void setUp() {
        data = null;
    }

    @Test
    public void success() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME, new Handler());

        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> assertThat(data).isEqualTo(someData));
    }

    @Test
    public void error_withHandler() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler(),
                new ErrorHandler());

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
    public void error_withoutHandler() {
        DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME,
                new ExceptionThrowingHandler());

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

        @Override
        public void onEvent(String data) {
            throw new IllegalArgumentException("data = " + data);
        }
    }
}
