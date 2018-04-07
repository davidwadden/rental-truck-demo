package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.kafka.core.KafkaTemplate;

public class KafkaAsyncEventSubscriber<T> extends AsyncEventSubscriber<T> {

    private KafkaTemplate<Object, T> template;

    public KafkaAsyncEventSubscriber(String eventName, KafkaTemplate<Object, T> template) {
        super(eventName);
        this.template = template;
    }

    @Override
    protected void onEvent(T data) {
        template.send(getEventName(), data);
    }
}
