package ir.piana.dev.jpos.app.participant;

import ir.piana.dev.jpos.app.logger.MainLogger;
import org.jpos.transaction.TransactionParticipant;

import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/9/2017 1:09 PM
 */
public class BalanceResponse
        implements TransactionParticipant {
    @Override
    public int prepare(long id, Serializable context) {
        return PREPARED;
    }

    @Override
    public void commit(long id, Serializable context) {
       MainLogger.log("balance response commit");
    }

    @Override
    public void abort(long id, Serializable context) {
        MainLogger.log("balance response abort");
    }
}
