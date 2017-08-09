package ir.piana.dev.jpos.client;

import ir.piana.dev.webtool2.server.PianaAnnotationAppMain;
import ir.piana.dev.webtool2.server.annotation.PianaServer;
import ir.piana.dev.webtool2.server.annotation.PianaSpaceProperties;
import ir.piana.dev.webtool2.server.annotation.PianaSpaceProperty;
import org.jpos.iso.*;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.q2.Q2;

import java.io.IOException;

/**
 * Created by SYSTEM on 8/7/2017.
 */
@PianaServer(httpPort = 2001)
@PianaSpaceProperties(properties = {
    @PianaSpaceProperty(name = "space-in", value = "client-in"),
        @PianaSpaceProperty(name = "space-out", value = "client-out")
})

public class MainClient {
//    public static void main(String[] args) throws ISOException {
//        ISOFieldPackager ifaNumeric = new IFA_NUMERIC(6, "Message Type Indicator");
//        byte[] pack1 = ifaNumeric.pack(new ISOField(1, "310000"));
//        ISOField isoField = new ISOField();
//        ifaNumeric.unpack(isoField, new byte[] {3, 1, 0, 0, 0, 0}, 0);
//
//
//        ISOMsg isoMsg = new ISOMsg("0100");
//        isoMsg.setPackager(new ISO87BPackager());
//        isoMsg.set(2, "310000");
//        byte[] pack = isoMsg.pack();
//
//
//
//        ISOMsg isoMsg2 = new ISOMsg();
//        isoMsg2.setPackager(new ISO87BPackager());
//        int unpack = isoMsg2.unpack(pack);
//
//        System.out.println(isoMsg2.getComponent(2).getValue());
//    }

//    public static void main(String[] args) throws IOException, ISOException {
//        ISOChannel channel = new ASCIIChannel(
//                "127.0.0.1", 6001, new ISO87BPackager());
//        channel.connect();
//
//        ISOMsg msg1 = new ISOMsg("0200");
//        msg1.set(16, "3131");
//
//        ISOMsg msg2 = new ISOMsg("0200");
//        msg2.set(16, "3232");
//
//        ISOMsg msg3 = new ISOMsg("0200");
//        msg3.set(16, "3333");
//
//        new MyThread(channel, msg1).start();
//        new MyThread(channel, msg2).start();
//        new MyThread(channel, msg3).start();
//    }

    public static void main(String[] args)
            throws Exception {
//        String property = System.getProperty("user.dir");
//        System.setProperty("user.dir", property.concat("/piana-jpos-client/scratchdir"));
//        System.setProperty("user.dir", "D:\\git-project\\piana-switch\\piana-jpos-client\\scratchdir");
        PianaAnnotationAppMain.start(MainClient.class);
        Q2.main(args);
    }
}


class MyThread extends Thread {
    private final ISOChannel channel;
    private final ISOMsg msg;

    MyThread(ISOChannel channel, ISOMsg msg) {
        this.channel = channel;
        this.msg = msg;
    }

    public void run() {
        try {
            channel.send(msg);
            ISOMsg receive = channel.receive();
            System.out.println(msg.getString(16) + receive.getString(17));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}