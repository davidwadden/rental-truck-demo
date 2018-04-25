package io.pivotal.pal.data.framework.event.messaging;

import io.pivotal.pal.data.framework.event.DefaultSyncEventPublisher;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;

public class SpringMessagingSyncEventPublisher<C, R> extends DefaultSyncEventPublisher<C, R> implements MessageHandler {

    private MessageChannel output;

    public SpringMessagingSyncEventPublisher(String eventName, SubscribableChannel input, MessageChannel output) {
        super(eventName);
        this.output = output;
        input.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //noinspection unchecked
        C data = (C) message.getPayload();
        R response = publish(data);

        if (response != null) {
            Message<R> outputMessage = MessageBuilder.withPayload(response)
                    .setHeader("eventName", getEventName())
                    .build();
            output.send(outputMessage);
        }
    }
}
