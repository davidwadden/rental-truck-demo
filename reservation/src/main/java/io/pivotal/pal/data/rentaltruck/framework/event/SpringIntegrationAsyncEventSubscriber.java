package io.pivotal.pal.data.rentaltruck.framework.event;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class SpringIntegrationAsyncEventSubscriber<T> extends AsyncEventSubscriber<T> {

    private MessageChannel channel;

    public SpringIntegrationAsyncEventSubscriber(String eventName, MessageChannel channel) {
        super(eventName);
        this.channel = channel;
    }

    @Override
    protected void onEvent(T data) {
        channel.send(MessageBuilder.withPayload(data).build());
    }
}
