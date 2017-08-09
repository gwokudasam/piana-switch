package ir.piana.dev.jpos.client;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

/**
 * Created by SYSTEM on 8/7/2017.
 */
public class MessageConverter {
    public static byte[] convert(
            ISOPackager from,
            ISOPackager to,
            byte[] fromBytes)
            throws ISOException {
        ISOMsg m = new ISOMsg();
        m.setPackager (from);
        m.unpack (fromBytes);
        m.setPackager (to);
        return m.pack();
    }
}
