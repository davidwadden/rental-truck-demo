package io.pivotal.pal.data.rentaltruck.framework.event;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncEventTest {

    private String data;

    private DefaultAsyncEventPublisher<String> publisher = new DefaultAsyncEventPublisher<String>(eventName);
    private AsyncEventSubscriberAdapter<String> subscriber = new AsyncEventSubscriberAdapter<>(eventName, new Handler());

    private static final String eventName = "test";

    @Before
    public void setUp() {
        data = null;
    }

    @Test
    public void basicTest() throws Exception {
        String someData = "some-data";
        publisher.publish(someData);

        Thread.sleep(1000);

        assertThat(data).isEqualTo(someData);
    }

    private class Handler implements AsyncEventHandler<String> {

        @Override
        public void onEvent(String data) {
            AsyncEventTest.this.data = data;
        }
    }
}
