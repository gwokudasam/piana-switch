package ir.piana.dev.jpos.app.participant;

import ir.piana.dev.jpos.app.logger.MainLogger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/9/2017 1:08 PM
 */
public class BalanceRequest
        implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable context) {
        ISOMsg m = (ISOMsg) ((Context) context)
                .get("REQUEST");
        try {
            m.setMTI("0110");
        } catch (ISOException e) {
            MainLogger.log(e.getMessage());
            return ABORTED;
        }
        return PREPARED;
    }

    @Override
    public void commit(long id, Serializable context) {
        MainLogger.log("balance request commit");
        MainLogger.log("balance request commit",
                "BalanceRequest4");

    }

    @Override
    public void abort(long id, Serializable context) {
        MainLogger.log("balance request abort",
                "BalanceRequest4");
    }
}
