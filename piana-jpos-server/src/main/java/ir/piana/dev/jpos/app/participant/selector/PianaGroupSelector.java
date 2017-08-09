package ir.piana.dev.jpos.app.participant.selector;

import org.jdom2.Element;
import org.jpos.core.ConfigurationException;
import org.jpos.core.XmlConfigurable;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable;

import static org.jline.utils.Log.warn;

/**
 * @author Mohammad Rahmati, 2/9/2017 12:55 PM
 */
public class PianaGroupSelector
        implements TransactionParticipant,
        GroupSelector, XmlConfigurable {
    private Element cfg;

    @Override
    public int prepare(long id, Serializable context) {
        return PREPARED | NO_JOIN | READONLY;
    }

    @Override
    public void commit(long id, Serializable context) {

    }

    @Override
    public void abort(long id, Serializable context) {

    }

    @Override
    public String select(long id, Serializable context) {

        try {
            ISOMsg m = (ISOMsg) ((Context) context)
                    .get("REQUEST");
            String mti = m.getMTI();
            if(mti.equalsIgnoreCase("0100"))
                return "balance-inquiry-request";
        } catch (Exception e) {
            warn (e);
            return null;
        }
        return null;
    }

    @Override
    public void setConfiguration(Element e)
            throws ConfigurationException {
        this.cfg = e;
    }
}
