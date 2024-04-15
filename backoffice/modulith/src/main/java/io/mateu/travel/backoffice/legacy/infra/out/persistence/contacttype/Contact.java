package io.mateu.travel.backoffice.legacy.infra.out.persistence.contacttype;

import io.mateu.travel.openjpa.OpenJPAL2EventPublisher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;


/*
busso=# \d vu_contact
                     Tabla «public.vu_contact»
       Columna        |            Tipo             | Modificadores
----------------------+-----------------------------+---------------
 conidcon             | bigint                      | not null
 concomment           | character varying(500)      |
 concompanyname       | character varying(250)      |
 conemail             | character varying(250)      |
 confax               | character varying(100)      |
 conmovil             | character varying(100)      |
 conname              | character varying(250)      |
 conprivateforhandler | boolean                     |
 conprivateforuser    | boolean                     |
 contelephone         | character varying(100)      |
 jdoclass             | character varying(255)      |
 jdoversion           | integer                     |
 conidctp             | bigint                      |
 conidhan             | bigint                      |
 conuser              | character varying(250)      |
 concreated           | timestamp without time zone |
 conmodified          | timestamp without time zone |
 concreatedby         | character varying(250)      |
 conmodifiedby        | character varying(250)      |
 conerased            | boolean                     |
 conweb               | character varying(250)      |
Índices:
    "vu_contact_pkey" PRIMARY KEY, btree (conidcon)



 */
@Entity
@Table(name = "vu_contact")
@Slf4j
@Getter@Setter
public class Contact {

    @Id
    @Column(name = "conidcon")
    long id;
    @Column(name = "jdoclass")
    String jdoclass;
    @Column(name = "jdoversion")
    int version;
    @Column(name = "conname")
    String name;

    @PostUpdate
    public void notifyUpdate() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
            public void afterCommit(){


                try {
                    OpenJPAL2EventPublisher.get().sendUpdate(com.viajesurbis.model.incoming.common.Contact.class, id);
                } catch (IOException e) {
                    log.error("when notifying update for " + Contact.class.getName(), e);
                }



            }
        });
    }

}
