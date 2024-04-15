package io.mateu.travel.backoffice.booking.domain.model.aggregates.booking;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events.BookingCancelled;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events.BookingCreated;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.events.BookingUpdated;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.valueobjects.*;
import io.mateu.travel.backoffice.shared.domain.AggregateRoot;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
public class Booking extends AggregateRoot {

    private final BookingId id;
    private final LeadName leadName;
    private final ServiceDescription serviceDescription;
    private final Amount salePrice;
    private final BookingStatus status;

    private Booking(BookingId id, LeadName leadName, ServiceDescription serviceDescription, Amount salePrice, BookingStatus status) {
        this.id = id;
        this.leadName = leadName;
        this.serviceDescription = serviceDescription;
        this.salePrice = salePrice;
        this.status = status;
    }

    public static Mono<Booking> create(String id, String leadName, String serviceDescription, int salePrice) {
        return Mono.just(new Booking(
                new BookingId(id),
                new LeadName(leadName),
                new ServiceDescription(serviceDescription),
                new Amount(salePrice),
                BookingStatus.Confirmed
        )).doOnNext(booking -> booking.addEvent(new BookingCreated(booking.id)));
    }


    public Mono<Booking> update(String leadName, String serviceDescription, int salePrice) {
        return Mono.just(new Booking(
                id,
                new LeadName(leadName),
                new ServiceDescription(serviceDescription),
                new Amount(salePrice),
                status
        )).doOnNext(booking -> booking.addEvent(new BookingUpdated(booking.id)));
    }

    public Mono<Booking> cancel() {
        return Mono.just(new Booking(
                id,
                leadName,
                serviceDescription,
                salePrice,
                BookingStatus.Cancelled
        )).doOnNext(booking -> booking.addEvent(new BookingCancelled(booking.id)));
    }

}
