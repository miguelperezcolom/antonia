package io.mateu.travel.openjpa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Base64;

public class ObjectStreamTester {

    @Test
    //@Disabled
    void readsEvent() throws IOException, ClassNotFoundException {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        new Thread(() -> {
            /*
            338210047
0
10333
[B@10289886
RemoteCommitEvent(payload=0, addClasses=(empty collection), addIds=(empty collection), updates=com.viajesurbis.model.incoming.common.Contact-315773251, deletes=(empty collection), sourceId=modulith-id, senderIp=modulith)

             */

            //var rawEncoded = "rO0ABXcUAAAAABQorP8AAAGOvr6nIwAABLF1cgACW0Ks8xf4BghU4AIAAHhwAAAABLzWgIVzcgAqb3JnLmFwYWNoZS5vcGVuanBhLmV2ZW50LlJlbW90ZUNvbW1pdEV2ZW50AAAAAAAAAAEMAAB4cHcEAAAAAHBzcgAsamF2YS51dGlsLkNvbGxlY3Rpb25zJFVubW9kaWZpYWJsZUNvbGxlY3Rpb24ZQgCAy173HgIAAUwAAWN0ABZMamF2YS91dGlsL0NvbGxlY3Rpb247eHBzcgATamF2YS51dGlsLkFycmF5TGlzdHiB0h2Zx2GdAwABSQAEc2l6ZXhwAAAAA3cEAAAAA3BzcgAab3JnLmFwYWNoZS5vcGVuanBhLnV0aWwuSWS+jkGG4lr0ZQIAAUoAA19pZHhyACFvcmcuYXBhY2hlLm9wZW5qcGEudXRpbC5PcGVuSlBBSWR4BThFA9nRcwIAAloABHN1YnNMAAR0eXBldAARTGphdmEvbGFuZy9DbGFzczt4cAB2cgAtY29tLnZpYWplc3VyYmlzLm1vZGVsLmluY29taW5nLmNvbW1vbi5Db250YWN0AAAAAAAAAAAAAAB4cAAAAAAS0lFDcHhwdxgABWJ1c3NvAA9zZXJ2ZXIuZXN0ZWMuY3p4";
            //var rawEncoded = "rO0ABXcUAAAAABQorP8AAAAAAAAAAAAAKF11cgACW0Ks8xf4BghU4AIAAHhwAAAACG1vZHVsaXRoc3IAKm9yZy5hcGFjaGUub3BlbmpwYS5ldmVudC5SZW1vdGVDb21taXRFdmVudAAAAAAAAAABDAAAeHB3BAAAAABwc3IALGphdmEudXRpbC5Db2xsZWN0aW9ucyRVbm1vZGlmaWFibGVDb2xsZWN0aW9uGUIAgMte9x4CAAFMAAFjdAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hwc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAF3BAAAAAFzcgAab3JnLmFwYWNoZS5vcGVuanBhLnV0aWwuSWS+jkGG4lr0ZQIAAUoAA19pZHhyACFvcmcuYXBhY2hlLm9wZW5qcGEudXRpbC5PcGVuSlBBSWR4BThFA9nRcwIAAloABHN1YnNMAAR0eXBldAARTGphdmEvbGFuZy9DbGFzczt4cAF2cgAtY29tLnZpYWplc3VyYmlzLm1vZGVsLmluY29taW5nLmNvbW1vbi5Db250YWN0AAAAAAAAAAAAAAB4cAAAAAAS0lFDeHB3FwALbW9kdWxpdGgtaWQACG1vZHVsaXRoeA==";
            var rawEncoded = "rO0ABXcUAAAAABQorP8AAAAAAAAAAAAAKF11cgACW0Ks8xf4BghU4AIAAHhwAAAACG1vZHVsaXRoc3IAKm9yZy5hcGFjaGUub3BlbmpwYS5ldmVudC5SZW1vdGVDb21taXRFdmVudAAAAAAAAAABDAAAeHB3BAAAAABwc3IALGphdmEudXRpbC5Db2xsZWN0aW9ucyRVbm1vZGlmaWFibGVDb2xsZWN0aW9uGUIAgMte9x4CAAFMAAFjdAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hwc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAF3BAAAAAFzcgAab3JnLmFwYWNoZS5vcGVuanBhLnV0aWwuSWS+jkGG4lr0ZQIAAUoAA19pZHhyACFvcmcuYXBhY2hlLm9wZW5qcGEudXRpbC5PcGVuSlBBSWR4BThFA9nRcwIAAloABHN1YnNMAAR0eXBldAARTGphdmEvbGFuZy9DbGFzczt4cAF2cgAtY29tLnZpYWplc3VyYmlzLm1vZGVsLmluY29taW5nLmNvbW1vbi5Db250YWN0AAAAAAAAAAAAAAB4cAAAAAAS0lFDeHB3FwALbW9kdWxpdGgtaWQACG1vZHVsaXRoeA==";
            var bytes = Base64.getDecoder().decode(rawEncoded);

            try {
                out.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        var ois = new ObjectInputStream(in);

        //System.out.println(ois.readObject());
        System.out.println(ois.readLong());
        System.out.println(ois.readLong());
        System.out.println(ois.readInt());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());

    }


    @Test
    @Disabled
    void runsAsExpected() throws IOException, ClassNotFoundException {

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        new Thread(() -> {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(out);

                oos.writeLong(1);
                oos.writeInt(2);
                oos.writeObject("hola");
                oos.writeObject("hola de nuevo");

                Thread.sleep(2000);

                oos.writeLong(3);
                oos.writeInt(4);
                oos.writeObject("hola 2");
                oos.writeObject("hola de nuevo 2");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();

        var ois = new ObjectInputStream(in);

        //System.out.println(ois.readObject());
        System.out.println(ois.readLong());
        System.out.println(ois.readInt());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());

        System.out.println("---");

        System.out.println(ois.readLong());
        System.out.println(ois.readInt());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());


    }
}
