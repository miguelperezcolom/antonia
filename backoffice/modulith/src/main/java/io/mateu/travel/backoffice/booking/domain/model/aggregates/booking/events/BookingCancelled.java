package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;

public record BookingCancelled(BookingId bookingId) {
}
