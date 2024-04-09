package io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype;

import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeEntityRepository extends JpaRepository<ContactType, String> {
}
