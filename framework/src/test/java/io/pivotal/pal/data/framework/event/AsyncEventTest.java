package io.pivotal.pal.data.framework.event;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

public class AsyncEventTest {

    private static final String EVENT_NAME = "test";

    private String data;

    private DefaultAsyncEventPublisher<String> publisher;
    private AsyncEventSubscriberAdapter<String> subscriber;

    @Before
    public void setUp() {
        publisher = new DefaultAsyncEventPublisher<>(EVENT_NAME);
        subscriber = new AsyncEventSubscriberAdapter<>(EVENT_NAME, new Handler());

        data = null;
    }

    @Test
    public void basicTest() {
        String someData = "some-data";
        publisher.publish(someData);

        await()
                .atMost(ONE_SECOND)
                .untilAsserted(() -> assertThat(data).isEqualTo(someData));
    }

    private class Handler implements AsyncEventHandler<String> {

        @Override
        public void onEvent(String data) {
            AsyncEventTest.this.data = data;
        }
    }
}
