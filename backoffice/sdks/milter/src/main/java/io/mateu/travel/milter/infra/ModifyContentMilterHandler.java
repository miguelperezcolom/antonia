package io.mateu.travel.milter.infra;

import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.nightcode.milter.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ModifyContentMilterHandler extends AbstractMilterHandler {

    private final Map<String, String> bodies = new HashMap();
    private final MilterReplacementEntityRepository milterReplacementEntityRepository;

    ModifyContentMilterHandler(Actions milterActions, ProtocolSteps milterProtocolSteps,
                               MilterReplacementEntityRepository milterReplacementEntityRepository) {
        super(milterActions, milterProtocolSteps);
        this.milterReplacementEntityRepository = milterReplacementEntityRepository;
    }

    @Override
    public void body(MilterContext context, byte[] bodyChunk) throws MilterException {
        String id = context.id().toString();
        if (bodyChunk != null) {
            bodies.put(id, bodies.getOrDefault(id, "") + new String(bodyChunk));
        }
        super.body(context, bodyChunk);
    }

    @Override
    public void eom(MilterContext context, @Nullable byte[] bodyChunk) throws MilterException {
        String id = context.id().toString();
        if (bodyChunk != null) {
            bodies.put(id, bodies.getOrDefault(id, "") + new String(bodyChunk));
        }
        String newBoody = bodies.getOrDefault(id, "");
        bodies.remove(id);
        for (MilterReplacementEntity milterReplacementEntity : milterReplacementEntityRepository.findAll()) {
            log.info("replacing %s with %s"
                    .formatted(milterReplacementEntity.getRegex(), milterReplacementEntity.getReplacement()));
            newBoody = newBoody
                    .replaceAll(milterReplacementEntity.getRegex(), milterReplacementEntity.getReplacement());
        }
        this.messageModificationService.replaceBody(context, newBoody.getBytes(StandardCharsets.UTF_8));
        super.eom(context, bodyChunk);
    }

    @Override
    public void quit(MilterContext milterContext) {

    }
}
