package io.pivotal.pal.data.rentaltruck.framework.event;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaAsyncEventSubscriber<T> extends AsyncEventSubscriber<T> {

    private KafkaProducer<Object, T> producer;

    public KafkaAsyncEventSubscriber(String eventName, KafkaProducer<Object, T> producer) {
        super(eventName);
        this.producer = producer;
    }

    @Override
    protected void onEvent(T data) {
        producer.send(new ProducerRecord<>(getEventName(), data));
    }
}
