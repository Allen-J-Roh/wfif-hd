package com.erae.mig.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    public static int     FATAL    = 0;
    public static int     ERROR    = 1;
    public static int     WARN     = 2;
    public static int     INFO     = 3;
    public static int     DEBUG    = 4;

    private static Log    instance = null;
    private static Logger logger   = null;

    public static void log(String stmsg) {
        log(stmsg, ERROR);
    }

    public static void printError(Exception e) {
        logger.error("WF-IF ERROR OCCURED ",e);
    }
    
    public static void log(String stmsg, int ilevel) {

        if (instance == null) {
            instance = new Log();
            PropertyConfigurator.configure(Log.class.getResource("log4j.properties"));
            logger = Logger.getLogger(Log.class);
        }

        switch (ilevel) {
        case 0:
            logger.fatal(stmsg);
            break;
        case 1:
            logger.error(stmsg);
            break;
        case 2:
            logger.warn(stmsg);
            break;
        case 3:
            logger.info(stmsg);
            break;
        case 4:
            logger.debug(stmsg);
            break;
        default:
            logger.error(stmsg);
        }
    }
}
