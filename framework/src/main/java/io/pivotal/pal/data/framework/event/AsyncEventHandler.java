package io.pivotal.pal.data.framework.event;

/**
 * Interface to define event subscriber implementations.
 *
 * @param <T> type of event handled
 */
public interface AsyncEventHandler<T> {

    /**
     * The event framework will call this method upon processing of an Event.
     *
     * @param data the event
     */
    void onEvent(T data);
}
