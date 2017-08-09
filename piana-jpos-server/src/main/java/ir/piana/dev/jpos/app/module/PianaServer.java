package ir.piana.dev.jpos.app.module;

import ir.piana.dev.jpos.app.logger.MainLogger;
import org.jdom2.Element;
import org.jpos.core.ConfigurationException;
import org.jpos.core.XmlConfigurable;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.q2.QBeanSupport;
import org.jpos.q2.iso.QServer;

/**
 * Created by SYSTEM on 8/9/2017.
 */
public class PianaServer
        extends QServer {
    @Override
    public void notify(Object key, Object value) {
        if ("sender".equalsIgnoreCase(sendMethod)) {
            Object obj = sp.inp(key);
            if (obj instanceof ISOMsg) {
                ISOMsg m = (ISOMsg) obj;

                try {
                    ISOSource c = m.getSource();
                    if (c == null) {
                        throw new ISOException("Server has no active connections");
                    }
                    if (!c.isConnected()) {
                        throw new ISOException("Client disconnected");
                    }
                    c.send(m);
                } catch (Exception e) {
                    getLog().warn("notify", e);
                }
            }
        } else
            super.notify(key, value);
    }
}

