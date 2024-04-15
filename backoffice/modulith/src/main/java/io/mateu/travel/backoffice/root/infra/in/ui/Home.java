package io.mateu.travel.backoffice.root.infra.in.ui;

import io.mateu.mdd.core.interfaces.HasAppTitle;
import io.mateu.mdd.shared.annotations.*;
import io.mateu.mdd.shared.interfaces.JpaCrud;
import io.mateu.travel.backoffice.booking.infra.in.ui.BookingMenu;
import io.mateu.travel.backoffice.legacy.infra.in.ui.LegacyMenu;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import lombok.Getter;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;

@MateuUI("")
@Getter
@Caption("Welcome")
@KeycloakSecured(url = "https://lemur-10.cloud-iam.com/auth", realm = "mateu", clientId = "cliente")
public class Home implements HasAppTitle, Secured {

    //todo: only when role is present
    @Submenu
    LegacyMenu legacy;

    //todo: only when role is present
    @Submenu
    BookingMenu booking;

    @Section(value = "", card = false)
    @RawContent
    String home = """
            <p>Hi!</p>
            <p>Here you will be able to perform some actions which are not available in your system.
                This should make you more autonomous.</p>
            <p>I hope you enjoy this ;)</p>
            <p></p>
            <p>Miguel</p>
            """;

    @Override
    public String getAppTitle() {
        return "New Estec";
    }

    @Override
    public void isSecure(ServerRequest request) {
        //todo: check if role / scope is there
    }
}
