package com.rra.tracker.controller;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Logging {

    Logger logger = null;

    // User login module
    /**
     * Creates a new instance of ErrorLog
     */
    String strDate = "";
    String strFileToLog = "";
    static String ipAddress = "";

//    public Logger logger=Logger.getLogger("com.eagle.common");
    /**
     * Creates a new instance of ErrorLog
     */
    public Logging() {

        try {
            logger = Logger.getLogger("com.eagle.common");
            strDate = getFormatDate(new java.util.Date(), "dd-MMM-yyyy");
            String getDirToLog = "AuditLog_Scheduler";

            String osname = System.getProperty("os.name");
            String slash = "";
            if (osname.startsWith("Windows")) {
                slash = "\\";
            } else {
                if ((osname.startsWith("Linux"))) {
                    slash = "/";
                }
            }
            File createDir = new File(getDirToLog);
            if (createDir.mkdirs()) {
                strFileToLog = createDir.getAbsolutePath() + slash + strDate + ".txt";
            } else if (!createDir.mkdir()) {
                strFileToLog = createDir.getAbsolutePath() + slash + strDate + ".txt";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Logging
            (String sessiondate) {
        try {
            logger = Logger.getLogger("com.eagle.common");

            strDate =sessiondate;

            String osname = System.getProperty("os.name");
            String slash = "";
            if (osname.startsWith("Windows")) {
                slash = "\\";
            } else {
                if ((osname.startsWith("Linux"))) {
                    slash = "/";
                }
            }
            String subfolder = "AuditLog_Scheduler" + slash + getFormatDate(new java.util.Date(), "dd-MMM-yyyy");
            strDate = getFormatDate(new Date(), "dd-MMM-yyyy");
            File createDir = new File(subfolder);
            if (createDir.mkdirs()) {
                strFileToLog = createDir.getAbsolutePath() + slash + strDate + ".txt";
            } else if (!createDir.mkdir()) {
                strFileToLog = createDir.getAbsolutePath() + slash + strDate + ".txt";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void writeLogJson(Date sessiodate, String event) throws IOException, IOException {

        String filename = new Logging(sessiodate.toString()).strFileToLog.trim();
        FileWriter handler = new FileWriter(filename, true);
        try {

            String strDate = getFormatDate(new Date(), "dd-MMM-yyyy hh:mm:ss");
            JSONObject obj = new JSONObject();
            obj.put("0", strDate);

            obj.put("1", event);

            handler.append(obj.toString());
            handler.append(",");
            handler.append(System.lineSeparator());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handler.close();
        }
    }
    public static String getFormatDate(Date dateValue, String format) {
        try {
            SimpleDateFormat sdfFormat = new SimpleDateFormat(format);
            return sdfFormat.format(dateValue);
        } catch (Exception e) {
            return "Invalid Format";
        }
    }

}
