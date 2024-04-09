package io.mateu.travel.backoffice.legacy.infra.out.persistence.billingkey;

import io.mateu.mdd.shared.annotations.Ignored;
import io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype.Contact;
import io.mateu.travel.openjpa.OpenJPAL2EventPublisher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;

@Entity
@Table(name = "VU_BILLINGKEY")
@Slf4j
@Getter
@Setter
public class BillingKey {

    @Id
    @Column(name = "BKYCODE")
    String code;
    @Column(name = "jdoclass")
            @Ignored
    String jdoclass;
    @Column(name = "jdoversion")
            @Ignored
    int version;
    @Column(name = "BKYNAME")
    String name;
    @Column(name = "BKYIVAPERCENT")
    double percent;

    @PostUpdate
    public void notifyUpdate() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
            public void afterCommit(){


                try {
                    OpenJPAL2EventPublisher.get().sendUpdate(com.viajesurbis.model.billing.BillingKey.class, code);
                } catch (IOException e) {
                    log.error("when notifying update for " + BillingKey.class.getName(), e);
                }



            }
        });
    }

}
