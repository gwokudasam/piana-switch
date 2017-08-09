package ir.piana.dev.jpos.client.rest;

import ir.piana.dev.webtool2.server.annotation.Handler;
import ir.piana.dev.webtool2.server.annotation.MethodHandler;
import ir.piana.dev.webtool2.server.annotation.PianaSpaceProvider;
import ir.piana.dev.webtool2.server.response.PianaResponse;
import ir.piana.dev.webtool2.server.session.Session;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.iso.ChannelAdaptor;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;

import javax.ws.rs.core.Response;

/**
 * Created by SYSTEM on 8/9/2017.
 */
@Handler(baseUrl = "iso-msg")
public class IsoMsgHandler {
    @PianaSpaceProvider(Key = "space-in")
    public static String spaceIn;

    @PianaSpaceProvider(Key = "space-out")
    public static String spaceOut;

    @MethodHandler()
    public static PianaResponse sendIsoMessage(Session session) throws ISOException {
        Space space = SpaceFactory.getSpace("tspace:default");
        ISOMsg isoMsg = new ISOMsg("0200");
//        isoMsg.set(2, new byte[] {49, 0, 0, 0, 0, 0});
        isoMsg.set(2, "310000");
        isoMsg.set(16, "0000");
        space.out(spaceIn, isoMsg);
        ISOMsg inMsg = (ISOMsg) space.in(spaceOut);

        return new PianaResponse(Response.Status.OK, 0, inMsg.getMTI());
    }
}
