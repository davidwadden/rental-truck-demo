package io.pivotal.pal.data.rentaltruck.framework.event;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncEventTest {

    private String data;

    private Publisher publisher = new Publisher();
    private Subscriber subscriber = new Subscriber();

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

    private class Publisher extends AsyncEventPublisher<String> {

        public Publisher() {
            super(eventName);
        }
    }

    private class Subscriber extends AsyncEventSubscriber<String> {

        public Subscriber() {
            super(eventName);
        }

        @Override
        protected void onEvent(String data) {
            AsyncEventTest.this.data = data;
        }
    }
}
