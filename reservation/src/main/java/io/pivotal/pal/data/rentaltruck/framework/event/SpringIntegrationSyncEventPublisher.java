package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;

public class SpringIntegrationSyncEventPublisher<C, R> extends SyncEventPublisher<C, R> implements MessageHandler {

    private MessageChannel output;

    public SpringIntegrationSyncEventPublisher(String eventName, SubscribableChannel input, MessageChannel output) {
        super(eventName);
        this.output = output;
        input.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        C data = (C) message.getPayload();
        R response = publish(data);

        if (response != null) {
            output.send(MessageBuilder.withPayload(response).setHeader("eventName", getEventName()).build());
        }
    }
}
