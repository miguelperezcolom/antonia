package io.mateu.travel.backoffice.legacy.infra.in.ui;

import io.mateu.mdd.shared.annotations.*;
import io.mateu.mdd.shared.interfaces.JpaCrud;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.billingkey.BillingKey;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype.Contact;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype.ContactType;
import io.mateu.travel.backoffice.root.infra.in.ui.Secured;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;

public class LegacyMenu implements Secured {

//    @MenuOption
//    JpaCrud<ContactType> contactTypes;
//
//    @MenuOption
//    JpaCrud<Contact> contacts;

    @MenuOption
    JpaCrud<BillingKey> vatPercents = new JpaCrud<BillingKey>() {
        @Override
        public boolean canAdd() {
            return false;
        }

        @Override
        public boolean canDelete() {
            return false;
        }
    };

    @MenuOption
    JpaCrud<MilterReplacementEntity> emailReplacements = new JpaCrud<>() {
        @Override
        public List<String> getColumnFields() {
            return List.of("regex", "replacement");
        }
    };

    @Override
    public void isSecure(ServerRequest request) {
        //todo: check roles
    }
}
