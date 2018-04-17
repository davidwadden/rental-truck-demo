package io.pivotal.pal.data.rentaltruck.framework.event;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncEventTest {

    private Publisher publisher = new Publisher();
    private Subscriber subscriber = new Subscriber();

    private static final String eventName = "test";

    @Before
    public void setUp() {
        subscriber.setData(null);
    }

    @Test
    public void basicTest() throws Exception {
        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(subscriber.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
    }

    private class Publisher extends SyncEventPublisher<String, String> {

        public Publisher() {
            super(eventName);
        }
    }

    private class Subscriber extends SyncEventSubscriber<String, String> {

        private String data;

        public Subscriber() {
            super(eventName);
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        protected String onEvent(String data) {
            this.data = data;
            return data;
        }
    }
}
