package ir.piana.dev.jpos.app.listener;

import org.jdom2.Element;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.core.XmlConfigurable;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Mohammad Rahmati, 2/9/2017 12:01 PM
 */
public class RequestListener
        implements ISORequestListener,
        Configurable, XmlConfigurable {
    protected Space sp;
    protected String outQueue;
    protected String inQueue;

    public boolean process(
            ISOSource source, ISOMsg m) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            m.getSource();
//            m.setSource(source);
            m.setMTI("210");
            m.set(17, m.getString(16));
//            source.send(m);
            sp.out(outQueue, m);
        } catch (ISOException e) {
            e.printStackTrace();
        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

//        Context context = new Context();
//        context.put("REQUEST", m);
//        context.put("SOURCE", source);
//        sp.out(outQueue, context);
        return true;
    }

    public void setConfiguration(Element e)
            throws ConfigurationException {
        List<Element> children = e.getChildren();
        children.forEach(
                element -> {
                    String name = element.getAttributeValue("name");
                    String value = element.getAttributeValue("value");
                    System.out.println(name + ":" + value);

                    if(name.equalsIgnoreCase("output-queue"))
                        outQueue = value;
                    else if(name.equalsIgnoreCase("input-queue"))
                        inQueue = value;
                });
        sp = SpaceFactory.getSpace("tspace:default");
    }

    @Override
    public void setConfiguration(Configuration cfg)
            throws ConfigurationException {
        Set<String> strings = cfg.keySet();
    }
}
