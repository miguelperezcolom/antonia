package io.mateu.travel.openjpa;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OpenJPAL2EventPublisher {

    public void sendUpdate(Class type, String id) throws IOException {
        var updates = List.of(new StringId(type, id));
        String sourceId = "xx";
        String senderIp = "localhost";
        send(new RemoteCommitEvent(RemoteCommitEvent.PAYLOAD_OIDS, null, null, updates, null, sourceId, senderIp));
    }

    private void send(RemoteCommitEvent event) throws IOException {

        Socket socket = new Socket("xxx", 1201);
        var dataOutputStream = new DataOutputStream(socket.getOutputStream());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeLong(338210047L);
        oos.writeLong(90001L); // long
        oos.writeInt(121212); // int
        oos.writeObject("localhost".getBytes(StandardCharsets.UTF_8)); //byte[]
        oos.writeObject(event);
        oos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();


        dataOutputStream.write(bytes);
        dataOutputStream.flush();
        dataOutputStream.close();

    }

}
