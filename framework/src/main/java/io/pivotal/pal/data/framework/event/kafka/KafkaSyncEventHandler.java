package io.pivotal.pal.data.framework.event.kafka;

import io.pivotal.pal.data.framework.event.SyncEventHandler;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSyncEventHandler<C, R> implements SyncEventHandler<C, R> {

    private String eventName;
    private KafkaTemplate<Object, C> template;

    public KafkaSyncEventHandler(String eventName, KafkaTemplate<Object, C> template) {
        this.eventName = eventName;
        this.template = template;
    }

    @Override
    public R onEvent(C data) {
        template.send(eventName, data);
        return null;
    }
}
