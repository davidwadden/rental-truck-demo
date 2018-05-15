package io.pivotal.pal.data.framework.event;

/**
 * Interface to define event subscriber implementations.
 *
 * @param <C> command type
 * @param <R> return type
 */
public interface SyncEventHandler<C, R> {

    /**
     * The event framework will call this method upon processing of an Event.
     *
     * @param event the event being emitted
     * @return the return value of processing the event
     */
    R onEvent(C event);
}
