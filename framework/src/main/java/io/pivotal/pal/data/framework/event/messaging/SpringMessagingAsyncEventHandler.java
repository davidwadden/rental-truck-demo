package io.pivotal.pal.data.framework.event.messaging;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringMessagingAsyncEventHandler<T> implements AsyncEventHandler<T> {

    private String eventName;
    private MessageChannel channel;

    public SpringMessagingAsyncEventHandler(String eventName, MessageChannel channel) {
        this.eventName = eventName;
        this.channel = channel;
    }

    @Override
    public void onEvent(T data) {
        Message<T> message = MessageBuilder.withPayload(data)
                .setHeader("eventName", eventName)
                .build();
        channel.send(message);
    }
}
