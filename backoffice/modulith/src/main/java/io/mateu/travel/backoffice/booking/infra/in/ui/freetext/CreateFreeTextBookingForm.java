package io.mateu.travel.backoffice.booking.infra.in.ui.freetext;

import io.mateu.core.domain.uidefinition.shared.annotations.Action;
import io.mateu.travel.backoffice.booking.application.CreateFreeTextBookingRequest;
import io.mateu.travel.backoffice.booking.application.CreateFreeTextBookingUseCase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Getter@Setter
public class CreateFreeTextBookingForm {

    private final CreateFreeTextBookingUseCase createFreeTextBookingUseCase;

    private String leadName;

    private String title;

    private String serviceDescription;

    private LocalDate startDate;

    private LocalDate endDate;

    public CreateFreeTextBookingForm(CreateFreeTextBookingUseCase createFreeTextBookingUseCase) {
        this.createFreeTextBookingUseCase = createFreeTextBookingUseCase;
    }


    @Action
    public void create() {
        createFreeTextBookingUseCase.createFreeTextBooking(new CreateFreeTextBookingRequest(
                leadName,
                title,
                serviceDescription,
                startDate,
                endDate
        ));
    }
}
