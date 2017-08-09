package ir.piana.dev.jpos.app.logger;

import org.jpos.util.LogEvent;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogSource;

/**
 * @author Mohammad Rahmati, 2/25/2017 10:47 AM
 */
public class MainLogger extends SimpleLogSource {
    public static final String LOGGERS_DEFAULT_REALM = "";//"jsima";
    public static final String LOGGER_EXCEPTION_TAG_NAME = "";//"exception";
    public static final String LOGGER_INFO_TAG_NAME = "info";
    public static final String MAIN_LOGGER_NAME = "logger.main-logger";
    private static final String DATA_ITEM_SEPARATOR = "\n    ";
    private static final String HEADER_ITEM_SEPARATOR = "\n  ";
    private static MainLogger instance;
    private static boolean loggerDisabled;

    private MainLogger() {
        logger = Logger.getLogger("main-logger");
        if (!logger.hasListeners())
            logger = Logger.getLogger("Q2");
        setLogger(logger, LOGGERS_DEFAULT_REALM);
    }

    public static MainLogger getInstance() {
        if (instance != null)
            return instance;
        synchronized (MainLogger.class) {
            if (instance != null)
                return instance;
            instance = new MainLogger();
        }
        return instance;
    }

    public static void log(String message) {
        Logger.log(new LogEvent(getInstance(), "INFO", message));
    }

    public static void log(String message, String realm) {
        getInstance().setLogger(Logger.getLogger("main-logger"), realm);
        Logger.log(new LogEvent(getInstance(), "INFO", message));
    }
}
