package io.pivotal.pal.data.rentaltruck.framework.event;

public interface AsyncEventHandler<T> {

    void onEvent(T data);
}
