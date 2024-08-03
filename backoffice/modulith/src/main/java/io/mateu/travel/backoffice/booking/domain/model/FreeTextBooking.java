package io.mateu.travel.backoffice.booking.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter@Setter
@Entity
@Builder@NoArgsConstructor@AllArgsConstructor
public class FreeTextBooking {

    @Id
    private String id;

    @NotBlank
    private String leadName;

    @NotBlank
    private String title;

    @NotBlank
    private String serviceDescription;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

}
