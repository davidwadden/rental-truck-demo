package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverified;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;

public class CreditCardVerifiedEventHandler implements AsyncEventHandler<CreditCardVerifiedEvent> {

    @Override
    public void onEvent(CreditCardVerifiedEvent data) {

        // 1. upsert to reservation table(s) that dont exist yet

        // 2a. if record inserted, send message to counter handler
        //  > publish reservation confirmed event to publisher

        // 3. if record updated, no-op

        // exceptional case:
        //   - retry if infrastructure problem  (Can't do too much, maybe 3X)
        //   - else, send to manual dead letter queue
        //   - what if data size exceeds column length ?  non-recoverable error
    }
}
