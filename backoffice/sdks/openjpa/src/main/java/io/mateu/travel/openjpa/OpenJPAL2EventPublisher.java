package io.mateu.travel.openjpa;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.openjpa.event.RemoteCommitEvent;
import org.apache.openjpa.util.Id;
import org.apache.openjpa.util.LongId;
import org.apache.openjpa.util.StringId;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class OpenJPAL2EventPublisher {

    private static OpenJPAL2EventPublisher _instance;

    public static OpenJPAL2EventPublisher get() {
        return _instance;
    }

    @PostConstruct
    void init() {
        _instance = this;
    }

    public void sendUpdate(Class type, String id) throws IOException {
        var updates = createJava8CompatibleListOf(new StringId(type, id));
        String sourceId = "xx";
        String senderIp = "localhost";
        send(new RemoteCommitEvent(RemoteCommitEvent.PAYLOAD_OIDS, null, null, updates, null, sourceId, senderIp));
    }

    private List createJava8CompatibleListOf(Object... values) {
        var l = new ArrayList();
        for (Object value : values) {
            if (value != null) {
                l.add(value);
            }
        }
        return l;
    }

    public void sendUpdate(Class type, long id) throws IOException {
        var updates = createJava8CompatibleListOf(new Id(type, id));
        String sourceId = "modulith-id";
        String senderIp = "modulith";
        send(new RemoteCommitEvent(RemoteCommitEvent.PAYLOAD_OIDS, null, null, updates, null, sourceId, senderIp));
    }

    private void send(RemoteCommitEvent event) throws IOException {

        var remotes = List.of(new RemoteServer("server.estec.cz", 9945)
                , new RemoteServer("server.estec.cz", 1201)
                //, new RemoteServer("localhost", 10333)
        );

        for (RemoteServer remote : remotes) {
            try {
                send(remote, event);
            } catch (Exception e) {
                log.error("when sending update to " + remote, e);
            }
        }

    }

    private void send(RemoteServer remote, RemoteCommitEvent event) throws IOException {
        Socket socket = new Socket(remote.host(), remote.port());
        log.info("sending event to " + remote);
        var dataOutputStream = new DataOutputStream(socket.getOutputStream());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeLong(338210047L); // version
        oos.writeLong(0L); // long _id any value is valid s it does not match a random id generated in remote
        oos.writeInt(10333); // int
        oos.writeObject("modulith".getBytes(StandardCharsets.UTF_8)); //byte[] servidor
        oos.writeObject(event);
        oos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();

        log.info("sending (raw): " + Base64.getEncoder().encodeToString(bytes));
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
        dataOutputStream.close();
        //todo: check if this is needed
        socket.close();
    }

}
