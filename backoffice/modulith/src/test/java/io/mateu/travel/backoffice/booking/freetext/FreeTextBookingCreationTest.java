package io.mateu.travel.backoffice.booking.freetext;

import io.mateu.travel.backoffice.booking.domain.model.BookingStatus;
import io.mateu.travel.backoffice.booking.domain.model.FreeTextBookingRepository;
import io.mateu.travel.backoffice.booking.infra.in.ui.freetext.CreateFreeTextBookingForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("local")
public class FreeTextBookingCreationTest {

    @Autowired
    CreateFreeTextBookingForm form;
    @Autowired
    FreeTextBookingRepository repository;

    @Test
    void bookingIsCreated() {
        // given
        var leadName = "Lead Name";
        var title = "Title";
        var serviceDescription = "Service Description";
        var startDate = LocalDate.now().plusDays(20);
        var endDate = LocalDate.now().plusDays(20);;

        form.setLeadName(leadName);
        form.setTitle(title);
        form.setServiceDescription(serviceDescription);
        form.setStartDate(startDate);
        form.setEndDate(endDate);

        // when
        form.create();

        // then
        var found = repository.findByLeadName(leadName);
        assertTrue(found.isPresent());
        var booking = found.get();
        assertEquals(leadName, booking.getLeadName());
        assertEquals(title, booking.getTitle());
        assertEquals(serviceDescription, booking.getServiceDescription());
        assertEquals(startDate, booking.getStartDate());
        assertEquals(endDate, booking.getEndDate());
        assertEquals(BookingStatus.Created, booking.getStatus());
    }


}
