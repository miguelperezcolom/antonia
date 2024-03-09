package io.mateu.travel.milter.infra;

import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nightcode.milter.Actions;
import org.nightcode.milter.MilterHandler;
import org.nightcode.milter.ProtocolSteps;
import org.nightcode.milter.net.MilterGatewayManager;
import org.nightcode.milter.net.ServerFactory;
import org.nightcode.milter.util.NetUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MilterPopulator {

    private final MilterReplacementEntityRepository milterReplacementEntityRepository;

    @PostConstruct
    public void init() {

        log.info("populating milter replacement entities");
        milterReplacementEntityRepository.save(new MilterReplacementEntity(
                UUID.randomUUID().toString(),
                "FEET PRAGUE 2020",
                "FEET PRAGUE 2022"));
        milterReplacementEntityRepository.save(new MilterReplacementEntity(
                UUID.randomUUID().toString(),
                "FEET PRAGUE 2020",
                "FEET PRAGUE 2022"));
        log.info("%d milter replacement entities created".formatted(milterReplacementEntityRepository.count()));

    }

}
