package io.mateu.travel.milter.infra.out.persistence.replacement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilterReplacementEntityRepository extends JpaRepository<MilterReplacementEntity, String> {
}
