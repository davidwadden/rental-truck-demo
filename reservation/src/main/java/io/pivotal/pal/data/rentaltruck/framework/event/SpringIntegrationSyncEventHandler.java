package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringIntegrationSyncEventHandler<C, R> implements SyncEventHandler<C, R> {

    private String eventName;
    private MessageChannel channel;

    public SpringIntegrationSyncEventHandler(String eventName, MessageChannel channel) {
        this.eventName = eventName;
        this.channel = channel;
    }

    @Override
    public R onEvent(C data) {
        channel.send(MessageBuilder.withPayload(data).setHeader("eventName", eventName).build());
        return null;
    }
}
