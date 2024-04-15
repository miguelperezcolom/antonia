package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.Amount;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.BookingId;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.LeadName;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.ServiceDescription;
import io.mateu.travel.backoffice.shared.domain.AggregateRoot;
import lombok.Getter;

@Getter
public class Booking implements AggregateRoot {

    private final BookingId id;
    private final LeadName leadName;
    private final ServiceDescription serviceDescription;
    private final Amount salePrice;

    public Booking(BookingId id, LeadName leadName, ServiceDescription serviceDescription, Amount salePrice) {
        this.id = id;
        this.leadName = leadName;
        this.serviceDescription = serviceDescription;
        this.salePrice = salePrice;
    }



    public void update() {

    }

    public void cancel() {

    }

}
