package io.pivotal.pal.data.framework.event;

import io.pivotal.pal.data.framework.event.kafka.KafkaAsyncEventPublisher;
import io.pivotal.pal.data.framework.event.messaging.SpringMessagingAsyncEventPublisher;

/**
 * This interface is the common API for asynchronous event publishers.
 *
 * @param <T> type of event
 *
 * @see DefaultAsyncEventPublisher
 * @see KafkaAsyncEventPublisher
 * @see SpringMessagingAsyncEventPublisher
 */
public interface AsyncEventPublisher<T> {

    /**
     * Publishes an event based using the underlying implementation.
     *
     * @param event the event to send
     */
    void publish(T event);
}
