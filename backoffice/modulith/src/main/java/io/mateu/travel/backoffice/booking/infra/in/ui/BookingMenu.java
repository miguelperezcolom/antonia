package io.mateu.travel.backoffice.booking.infra.in.ui;

import io.mateu.mdd.shared.annotations.MenuOption;
import io.mateu.mdd.shared.interfaces.JpaCrud;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.billingkey.BillingKey;
import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntity;

import java.util.List;

public class BookingMenu {

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

}
