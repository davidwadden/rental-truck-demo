package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSyncEventSubscriber<C, R> extends SyncEventSubscriber<C, R> {

    private KafkaTemplate<Object, C> template;

    public KafkaSyncEventSubscriber(String eventName, KafkaTemplate<Object, C> template) {
        super(eventName);
        this.template = template;
    }

    @Override
    protected R onEvent(C data) {
        template.send(getEventName(), data);
        return null;
    }
}
