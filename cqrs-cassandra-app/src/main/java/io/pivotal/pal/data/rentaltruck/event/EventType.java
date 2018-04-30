package io.pivotal.pal.data.rentaltruck.event;

public enum EventType {

    RESERVATION_REQUESTED("reservation-requested"),
    RESERVATION_INITIALIZED("reservation-initialized"),
    CREDIT_CARD_VERIFIED("credit-card-verified"),
    CREDIT_CARD_FAILED("credit-card-failed"),
    RESERVATION_VALIDATED("reservation-validated"),
    TRUCK_AVAILABLE("truck-available"),
    TRUCK_NOT_AVAILABLE("truck-not-available");

    private final String eventName;

    EventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
