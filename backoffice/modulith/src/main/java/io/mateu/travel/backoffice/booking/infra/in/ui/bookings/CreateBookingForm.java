package io.mateu.travel.backoffice.booking.infra.in.ui.bookings;

import io.mateu.mdd.shared.annotations.MainAction;
import io.mateu.travel.backoffice.booking.application.usecases.create.CreateBookingUseCase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Getter@Setter
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateBookingForm {

    @Autowired
    private CreateBookingUseCase useCase;

    String leadName;
    String serviceDescription;
    int salePrice;

    @MainAction
    public Mono<Void> create() {
        return useCase.createBooking(leadName, serviceDescription, salePrice);
    }

}
