package io.pivotal.pal.data.rentaltruck.framework.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.SmartLifecycle;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Map;

public class KafkaAsyncEventPublisher<T> extends DefaultAsyncEventPublisher<T> implements MessageListener<Object, T>, SmartLifecycle {

    private KafkaMessageListenerContainer<Object, T> container;
    private Map<String, Object> consumerProps;

    public KafkaAsyncEventPublisher(String eventName, Map<String, Object> consumerProps) {
        super(eventName);
        this.consumerProps = consumerProps;

        ContainerProperties containerProps = new ContainerProperties(eventName);
        containerProps.setMessageListener(this);
        this.container = createContainer(containerProps);
    }

    @Override
    public void onMessage(ConsumerRecord<Object, T> record) {
        T data = record.value();
        publish(data);
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
    }

    @Override
    public void start() {
        container.start();
    }

    @Override
    public void stop() {
        container.stop();
    }

    @Override
    public boolean isRunning() {
        return container.isRunning();
    }

    @Override
    public int getPhase() {
        return 0;
    }

    private KafkaMessageListenerContainer<Object, T> createContainer(ContainerProperties containerProps) {
        DefaultKafkaConsumerFactory<Object, T> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        return new KafkaMessageListenerContainer<>(cf, containerProps);
    }

}
