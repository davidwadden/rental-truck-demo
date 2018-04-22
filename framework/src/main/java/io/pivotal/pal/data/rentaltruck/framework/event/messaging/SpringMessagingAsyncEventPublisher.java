package io.pivotal.pal.data.rentaltruck.framework.event.messaging;

import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class SpringMessagingAsyncEventPublisher<T> extends DefaultAsyncEventPublisher<T> implements MessageHandler {

    public SpringMessagingAsyncEventPublisher(String eventName, SubscribableChannel channel) {
        super(eventName);
        channel.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //noinspection unchecked
        T data = (T) message.getPayload();
        publish(data);
    }
}
