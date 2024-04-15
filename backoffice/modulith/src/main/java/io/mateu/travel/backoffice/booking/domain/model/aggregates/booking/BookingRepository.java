package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;
import io.mateu.travel.backoffice.shared.domain.Repository;

public interface BookingRepository extends Repository<Booking, BookingId> {
}
