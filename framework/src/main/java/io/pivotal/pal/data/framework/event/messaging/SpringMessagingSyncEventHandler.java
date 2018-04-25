package io.pivotal.pal.data.framework.event.messaging;

import io.pivotal.pal.data.framework.event.SyncEventHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringMessagingSyncEventHandler<C, R> implements SyncEventHandler<C, R> {

    private String eventName;
    private MessageChannel channel;

    public SpringMessagingSyncEventHandler(String eventName, MessageChannel channel) {
        this.eventName = eventName;
        this.channel = channel;
    }

    @Override
    public R onEvent(C data) {
        Message<C> message = MessageBuilder.withPayload(data)
                .setHeader("eventName", eventName)
                .build();
        channel.send(message);

        return null;
    }
}
