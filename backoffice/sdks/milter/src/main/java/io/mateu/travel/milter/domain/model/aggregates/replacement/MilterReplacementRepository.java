package io.mateu.travel.milter.domain.model.aggregates.replacement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MilterReplacementRepository {

    Mono<MilterReplacement> findById();

    Mono<Void> save();

    Flux<MilterReplacement> findAll();

}
