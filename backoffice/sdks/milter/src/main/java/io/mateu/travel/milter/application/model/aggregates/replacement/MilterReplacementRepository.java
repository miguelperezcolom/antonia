package io.mateu.travel.milter.application.model.aggregates.replacement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface MilterReplacementRepository {

    Mono<MilterReplacement> findById();

    Mono<Void> save();

    Flux<MilterReplacement> findAll();

}
