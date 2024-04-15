package io.mateu.travel.backoffice.root.infra.in.ui;

import org.springframework.web.reactive.function.server.ServerRequest;

//todo: move to mateu
public interface Secured {

    void isSecure(ServerRequest request);

}
