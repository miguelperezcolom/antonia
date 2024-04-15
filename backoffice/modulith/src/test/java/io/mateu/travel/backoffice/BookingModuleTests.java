package io.mateu.travel.backoffice;

import io.mateu.travel.backoffice.booking.infra.in.ui.bookings.CreateBookingForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class BookingModuleTests {

    CreateBookingForm createBookingForm;

    @Test
    void bookingIsCreated() {

        //given
        // we filled the create booking form
        createBookingForm.setLeadName("Test lead name");
        createBookingForm.setServiceDescription("Test service description");
        createBookingForm.setSalePrice(102);

        //when
        // we click on save
        createBookingForm.create().block();

        //then
        // the booking is stored in the db
        // we have sent an event

    }

    @Test
    void bookingIsListed() {

        //given
        // we create a booking

        //when
        // we open the list and search

        //then
        // the booking is there

    }


    @Test
    void bookingIsUpdated() {

        //given
        // we filled the create booking form

        //when
        // we click on save

        //then
        // the booking is stored in the db
        // we have sent an event

    }

    @Test
    void bookingIsCancelled() {

        //given
        // we filled the create booking form

        //when
        // we click on save

        //then
        // the booking is stored in the db
        // we have sent an event

    }

}
