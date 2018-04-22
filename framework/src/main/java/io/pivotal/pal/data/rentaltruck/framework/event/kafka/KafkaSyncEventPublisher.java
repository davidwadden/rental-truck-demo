package io.pivotal.pal.data.rentaltruck.framework.event.kafka;

import io.pivotal.pal.data.rentaltruck.framework.event.DefaultSyncEventPublisher;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.SmartLifecycle;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Map;

public class KafkaSyncEventPublisher<C, R> extends DefaultSyncEventPublisher<C, R> implements MessageListener<Object, C>, SmartLifecycle {

    private KafkaMessageListenerContainer<Object, C> container;
    private Map<String, Object> consumerProps;

    public KafkaSyncEventPublisher(String eventName, Map<String, Object> consumerProps) {
        super(eventName);
        this.consumerProps = consumerProps;

        ContainerProperties containerProps = new ContainerProperties(eventName);
        containerProps.setMessageListener(this);
        this.container = createContainer(containerProps);
    }

    @Override
    public void onMessage(ConsumerRecord<Object, C> data) {
        publish(data.value());
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

    private KafkaMessageListenerContainer<Object, C> createContainer(ContainerProperties containerProps) {
        DefaultKafkaConsumerFactory<Object, C> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        return new KafkaMessageListenerContainer<>(cf, containerProps);
    }

}
