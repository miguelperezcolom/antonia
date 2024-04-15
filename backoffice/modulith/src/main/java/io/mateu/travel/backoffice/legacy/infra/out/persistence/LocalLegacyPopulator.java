package io.mateu.travel.backoffice.legacy.infra.out.persistence;

import io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype.ContactType;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype.ContactTypeEntityRepository;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalLegacyPopulator {

    private final ContactTypeEntityRepository contactTypeEntityRepository;

    @PostConstruct
    public void init() {

        if (contactTypeEntityRepository.count() > 0) {
            log.info("contact type entities already exist. Skipping population");
            return;
        }
        log.info("populating contact type entities");
        contactTypeEntityRepository.save(createContactType("test"));
        log.info("%d contact types entities created".formatted(contactTypeEntityRepository.count()));

    }

    private ContactType createContactType(String name) {
        ContactType contactType = new ContactType();
        contactType.setId(1);
        contactType.setJdoclass(com.viajesurbis.model.incoming.common.ContactType.class.getName());
        contactType.setName(name);
        contactType.setOrder(0);
        contactType.setVersion(1);
        return contactType;
    }

}
