package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.*;

public class SpringIntegrationAsyncEventPublisher<T> extends AsyncEventPublisher<T> implements MessageHandler {

    public SpringIntegrationAsyncEventPublisher(String eventName, SubscribableChannel channel) {
        super(eventName);
        channel.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        T data = (T) message.getPayload();
        publish(data);
    }
}
