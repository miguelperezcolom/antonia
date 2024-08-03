package io.mateu.travel.backoffice.booking.application;


import java.time.LocalDate;

public record CreateFreeTextBookingRequest(
        String leadName,
        String title,
        String serviceDescription,
        LocalDate startDate,
        LocalDate endDate) {
}
