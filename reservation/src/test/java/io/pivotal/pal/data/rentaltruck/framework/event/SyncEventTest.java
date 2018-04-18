package io.pivotal.pal.data.rentaltruck.framework.event;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncEventTest {

    private DefaultSyncEventPublisher<String, String> publisher = new DefaultSyncEventPublisher<String, String>(eventName);
    private Handler handler = new Handler();
    private SyncEventSubscriberAdapter<String, String> subscriber = new SyncEventSubscriberAdapter<>(eventName, handler);
    private static final String eventName = "test";

    @Before
    public void setUp() {
        handler.setData(null);
    }

    @Test
    public void basicTest() throws Exception {
        String someData = "some-data";
        String result = publisher.publish(someData);

        assertThat(handler.getData()).isEqualTo(someData);
        assertThat(result).isEqualTo(someData);
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
