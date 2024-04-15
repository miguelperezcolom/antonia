package io.mateu.travel.milter.infra.out.persistence.replacement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "MT_MILTERREPLACEMENT")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class MilterReplacementEntity {

    @Id private String id = UUID.randomUUID().toString();

    private String regex;

    private String replacement;

}
