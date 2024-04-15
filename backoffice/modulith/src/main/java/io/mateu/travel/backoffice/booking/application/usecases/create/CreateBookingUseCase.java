package io.mateu.travel.backoffice.booking.application.usecases.create;

import io.mateu.travel.backoffice.booking.application.usecases.create.commands.CreateBookingCommand;
import io.mateu.travel.backoffice.booking.application.usecases.create.commands.CreateBookingCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCase {

    private final CreateBookingCommandHandler createBookingCommandHandler;

    public Mono<Void> createBooking(String leadName, String serviceDescription, int salePrice) {
        String id = UUID.randomUUID().toString();
        return createBookingCommandHandler
                .handle(new CreateBookingCommand(id, leadName, serviceDescription, salePrice));
    }
}
