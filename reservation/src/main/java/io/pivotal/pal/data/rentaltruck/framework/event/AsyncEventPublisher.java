package io.pivotal.pal.data.rentaltruck.framework.event;

public interface AsyncEventPublisher<T> {

    void publish(T data);
}
