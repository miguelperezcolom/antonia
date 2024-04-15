package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;
import io.mateu.travel.backoffice.shared.domain.AggregateRoot;

public class Booking implements AggregateRoot {

    private final BookingId id;


    public Booking(BookingId id) {
        this.id = id;
    }

    public BookingId getId() {
        return id;
    }
}
