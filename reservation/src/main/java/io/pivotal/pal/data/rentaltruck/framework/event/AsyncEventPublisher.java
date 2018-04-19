package io.pivotal.pal.data.rentaltruck.framework.event;

/**
 * This interface is the common API for asynchronous event publishers.
 *
 * @param <T> type of event
 *
 * @see DefaultAsyncEventPublisher
 * @see KafkaAsyncEventPublisher
 * @see SpringIntegrationAsyncEventPublisher
 */
public interface AsyncEventPublisher<T> {

    /**
     * Publishes an event based using the underlying implementation.
     *
     * @param data the event to send
     */
    void publish(T data);
}
