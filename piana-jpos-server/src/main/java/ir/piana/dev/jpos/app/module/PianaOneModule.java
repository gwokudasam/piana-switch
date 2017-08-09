package ir.piana.dev.jpos.app.module;

import ir.piana.dev.jpos.app.logger.MainLogger;
import org.jdom2.Element;
import org.jpos.core.ConfigurationException;
import org.jpos.core.XmlConfigurable;
import org.jpos.q2.QBeanSupport;

/**
 * @author Mohammad Rahmati, 6/7/2017 12:35 PM
 */
public class PianaOneModule
        extends QBeanSupport implements XmlConfigurable {
    private Element e;

    protected void initService()
            throws Exception {
        MainLogger.log("init one module");
    }

    protected void startService()
            throws Exception {
        MainLogger.log("start one module");
    }

    protected void stopService()
            throws Exception {
        MainLogger.log("stop one module");
    }

    protected void destroyService()
            throws Exception {
        MainLogger.log("destroy one module");
    }

    @Override
    public void setConfiguration(Element e)
            throws ConfigurationException {
        this.e = e;
    }
}
