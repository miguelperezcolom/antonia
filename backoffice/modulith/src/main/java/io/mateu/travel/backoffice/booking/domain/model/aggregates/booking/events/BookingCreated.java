package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;

public record BookingCreated(BookingId bookingId) {
}
