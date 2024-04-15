package io.mateu.travel.backoffice.shared.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Repository<AggregateRootType, IdType> {

    Mono<AggregateRootType> findById(IdType id);

    Mono<Void> save(AggregateRootType aggregateRoot);

    Flux<AggregateRootType> findAll();

}
