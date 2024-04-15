package io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype;

import io.mateu.travel.openjpa.OpenJPAL2EventPublisher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/*
busso=# \d vu_contacttype
 ctpidctp   | bigint                 | not null
 ctpname    | character varying(400) |
 jdoclass   | character varying(255) |
 jdoversion | integer                |
 ctporder   | integer                |

busso=#

 */
@Entity
@Table(name = "vu_contacttype")
@Slf4j
@Getter@Setter
public class ContactType {

    @Id
    @Column(name = "ctpidctp")
    long id;
    @Column(name = "jdoclass")
    String jdoclass;
    @Column(name = "jdoversion")
    int version;
    @Column(name = "ctpname")
    String name;
    @Column(name = "ctporder")
    int order;

    @PostUpdate
    public void notifyUpdate() {
        try {
            OpenJPAL2EventPublisher.get().sendUpdate(com.viajesurbis.model.incoming.common.ContactType.class, id);
        } catch (IOException e) {
            log.error("when notifying update for " + ContactType.class.getName(), e);
        }
    }

}
