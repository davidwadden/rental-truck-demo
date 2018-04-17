package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringIntegrationSyncEventSubscriber<C, R> extends SyncEventSubscriber<C, R> {

    private MessageChannel channel;

    public SpringIntegrationSyncEventSubscriber(String eventName, MessageChannel channel) {
        super(eventName);
        this.channel = channel;
    }

    @Override
    protected R onEvent(C data) {
        channel.send(MessageBuilder.withPayload(data).setHeader("eventName", getEventName()).build());
        return null;
    }
}
