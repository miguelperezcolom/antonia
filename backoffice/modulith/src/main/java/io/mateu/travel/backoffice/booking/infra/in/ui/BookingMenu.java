package io.mateu.travel.backoffice.booking.infra.in.ui;

import io.mateu.core.domain.uidefinition.shared.annotations.MenuOption;
import io.mateu.core.domain.uidefinition.shared.interfaces.JpaCrud;
import io.mateu.travel.backoffice.booking.domain.model.FreeTextBooking;

public class BookingMenu {

    @MenuOption
    JpaCrud<FreeTextBooking> bookings = new JpaCrud<FreeTextBooking>() {
        @Override
        public boolean canAdd() {
            return false;
        }

        @Override
        public boolean canDelete() {
            return false;
        }
    };

}
