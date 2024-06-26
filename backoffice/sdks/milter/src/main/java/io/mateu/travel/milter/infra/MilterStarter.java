package io.mateu.travel.milter.infra;

import io.mateu.travel.milter.infra.out.persistence.replacement.MilterReplacementEntityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.nightcode.milter.Actions;
import org.nightcode.milter.MilterHandler;
import org.nightcode.milter.ProtocolSteps;
import org.nightcode.milter.net.MilterGatewayManager;
import org.nightcode.milter.net.ServerFactory;
import org.nightcode.milter.util.NetUtils;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@RequiredArgsConstructor
/**
 * creates and binds the milter service to address and port
 */
public class MilterStarter {

    private final MilterReplacementEntityRepository milterReplacementEntityRepository;

    @PostConstruct
    public void init() {
        // indicates what changes you intend to do with messages
        Actions milterActions = Actions.builder()
                .addHeader()
                .build();

        // indicates which steps you want to skip
        ProtocolSteps milterProtocolSteps = ProtocolSteps.builder()
                .noHelo()
                .noData()
                .build();

        // gateway address
        InetSocketAddress address = NetUtils
                .parseAddress(System.getProperty("jmilter.address", "0.0.0.0:4545"));
        ServerFactory<InetSocketAddress> serverFactory = ServerFactory.tcpIpFactory(address);

        // a simple milter handler that modifies the message body by replacing known patterns
        MilterHandler milterHandler = new ModifyContentMilterHandler(milterActions,
                milterProtocolSteps,
                milterReplacementEntityRepository);

        MilterGatewayManager<InetSocketAddress> gatewayManager =
                new MilterGatewayManager<>(serverFactory, milterHandler);

        gatewayManager.bind();

    }

}
