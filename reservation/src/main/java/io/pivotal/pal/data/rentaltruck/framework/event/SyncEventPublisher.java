package io.pivotal.pal.data.rentaltruck.framework.event;

public interface SyncEventPublisher<C, R> {

    R publish(C data);
}
