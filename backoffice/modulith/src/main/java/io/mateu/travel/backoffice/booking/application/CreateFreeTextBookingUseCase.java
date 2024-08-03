package io.mateu.travel.backoffice.booking.application;

import io.mateu.travel.backoffice.booking.domain.model.BookingStatus;
import io.mateu.travel.backoffice.booking.domain.model.FreeTextBooking;
import io.mateu.travel.backoffice.booking.domain.model.FreeTextBookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateFreeTextBookingUseCase {

    private final FreeTextBookingRepository freeTextBookingRepository;

    public CreateFreeTextBookingUseCase(FreeTextBookingRepository freeTextBookingRepository) {
        this.freeTextBookingRepository = freeTextBookingRepository;
    }

    @Transactional
    public void createFreeTextBooking(CreateFreeTextBookingRequest request) {
        String id = UUID.randomUUID().toString();
        var booking = FreeTextBooking.builder()
                .id(id)
                .leadName(request.leadName())
                .title(request.title())
                .serviceDescription(request.serviceDescription())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(BookingStatus.Created)
                .build();
        freeTextBookingRepository.save(booking);
    }
}
