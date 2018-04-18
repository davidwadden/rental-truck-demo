package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringIntegrationAsyncEventHandler<T> implements AsyncEventHandler<T> {

    private String eventName;
    private MessageChannel channel;

    public SpringIntegrationAsyncEventHandler(String eventName, MessageChannel channel) {
        this.eventName = eventName;
        this.channel = channel;
    }

    @Override
    public void onEvent(T data) {
        channel.send(MessageBuilder.withPayload(data).setHeader("eventName", eventName).build());
    }
}
