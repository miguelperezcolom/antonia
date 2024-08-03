package io.mateu.travel.backoffice.booking.domain.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FreeTextBookingRepository extends CrudRepository<FreeTextBooking, String> {

    Optional<FreeTextBooking> findByLeadName(String leadName);
}
