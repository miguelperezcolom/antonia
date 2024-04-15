package io.mateu.travel.backoffice.shared.application;

import reactor.core.publisher.Mono;

public interface CommandHandler<CommandType> {

    Mono<Void> handle(CommandType command);

}
