package io.mateu.travel.milter.infra.out.persistence.replacement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class MilterReplacementEntity {

    @Id private String id;

    private String regex;

    private String replacement;

}
