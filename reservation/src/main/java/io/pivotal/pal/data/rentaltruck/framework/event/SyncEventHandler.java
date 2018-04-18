package io.pivotal.pal.data.rentaltruck.framework.event;

public interface SyncEventHandler<C, R> {

    R onEvent(C data);
}
