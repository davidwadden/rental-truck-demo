package io.pivotal.pal.data.framework.event;

/**
 * This interface is the common API for synchronous event publishers.
 *
 * @param <C> command type
 * @param <R> return type
 */
public interface SyncEventPublisher<C, R> {

    /**
     * Publishes an event based using the underlying implementation.
     *
     * @param event the event to send
     * @return return value from processing the event
     */
    R publish(C event);
}
