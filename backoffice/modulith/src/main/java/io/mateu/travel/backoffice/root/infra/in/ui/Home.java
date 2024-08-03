package io.mateu.travel.backoffice.root.infra.in.ui;

import io.mateu.core.domain.uidefinition.core.interfaces.HasAppTitle;
import io.mateu.core.domain.uidefinition.shared.annotations.*;
import io.mateu.travel.backoffice.booking.infra.in.ui.BookingMenu;
import io.mateu.travel.backoffice.legacy.infra.in.ui.LegacyMenu;
import lombok.Getter;

@MateuUI("")
@Getter
@Caption("Welcome")
@KeycloakSecured(url = "https://lemur-10.cloud-iam.com/auth", realm = "mateu", clientId = "cliente")
public class Home implements HasAppTitle {

    @Submenu
    LegacyMenu legacy;

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

}
