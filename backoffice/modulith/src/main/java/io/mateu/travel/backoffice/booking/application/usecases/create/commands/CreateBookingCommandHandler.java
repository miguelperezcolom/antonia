package io.mateu.travel.backoffice.booking.application.usecases.create.commands;

import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.Booking;
import io.mateu.travel.backoffice.booking.domain.model.aggregates.booking.BookingRepository;
import io.mateu.travel.backoffice.shared.application.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateBookingCommandHandler implements CommandHandler<CreateBookingCommand> {

    private final BookingRepository bookingRepository;

    @Override
    public Mono<Void> handle(CreateBookingCommand command) {
        return Booking
                .create(command.id(), command.leadName(), command.serviceDescription(), command.salePrice())
                .flatMap(bookingRepository::save);
    }
}
