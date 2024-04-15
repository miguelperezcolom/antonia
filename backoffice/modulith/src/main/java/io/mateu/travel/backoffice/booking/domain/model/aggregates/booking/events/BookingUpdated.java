package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;
import io.mateu.travel.backoffice.shared.domain.DomainEvent;

public record BookingUpdated(BookingId bookingId) implements DomainEvent {
}
