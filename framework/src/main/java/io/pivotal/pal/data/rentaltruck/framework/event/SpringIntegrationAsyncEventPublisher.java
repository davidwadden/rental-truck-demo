package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class SpringIntegrationAsyncEventPublisher<T> extends DefaultAsyncEventPublisher<T> implements MessageHandler {

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
