package io.mateu.travel.openjpa;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.openjpa.event.RemoteCommitEvent;
import org.apache.openjpa.util.LongId;
import org.apache.openjpa.util.StringId;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class OpenJPAL2EventListener {

    @SneakyThrows
    @PostConstruct
    public void listen() {
        log.info("creating and starting listener thread");
        new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                log.info("creating socket at port 10333");
                serverSocket = new ServerSocket(10333);
                log.info("gonna accept remote events");
                try {
                    var clientSocket = serverSocket.accept();
                    log.info("accepting remote events");
                    var bis = new BufferedInputStream(clientSocket.getInputStream());
                    var bytes = new byte[50000];
                    var count = 0;
                    while(true) {
                        while ((count = bis.read(bytes)) != 0) {
                            var bytesRead = Arrays.copyOf(bytes, count); // remove trailing 0xAC
                            System.out.println(new String(Base64.getEncoder().encode(bytesRead)));
                            PipedInputStream in = new PipedInputStream();
                            PipedOutputStream out = new PipedOutputStream(in);

                            new Thread(() -> {
                                try {
                                    var ois = new ObjectInputStream(in);
                                    var version = ois.readLong();
                                    log.info("received long (version): " + version);
                                    try {
                                        parse(ois);
                                    } catch (Exception e) {
                                        log.error("while parsing stream", e);
                                    }
                                } catch (IOException e) {
                                    log.error("in reader thread", e);
                                }
                            }).start();

                            out.write(bytesRead);
                        }
                        log.info("finished reading");
                    }
                } catch (Exception e) {
                    log.error("not listening to remote events anymore", e);
                }
            } catch (IOException e) {
                log.error("when running listener thread ", e);
            }
        }).start();
    }

    private void parse(ObjectInputStream oos) throws IOException, ClassNotFoundException {
        log.info("received long (id): " + oos.readLong());
        log.info("received (int): " + oos.readInt());
        log.info("received (object): " + serialize(oos.readObject()));
        log.info("received (event): " + oos.readObject());
    }

    private String serialize(Object o) {
        if (o == null) {
            return "null";
        }
        if (o instanceof byte[]) {
            return new String((byte[]) o);
        }
        return "" + o;
    }

}
