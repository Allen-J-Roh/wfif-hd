package com.erae.mig.util;

import java.util.Calendar;

public class DateUtil {

    /**
     * 
     * @return "20070101"
     */
    public static String getParentDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = "" + year + (month < 10 ? "0" + month : month) + (day < 10 ? "0" + day : day);
        return date;
    }
    
    /**
     * 
     * @return "2007-01-01 11:11:11"
     */
    public static String getParentDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String hh = (cal.get(Calendar.HOUR_OF_DAY) < 10) ? (cal.get(Calendar.HOUR_OF_DAY)) + "0" : "" + (cal.get(Calendar.HOUR_OF_DAY));
        String mm = (cal.get(Calendar.MINUTE) < 10) ? "0" + (cal.get(Calendar.MINUTE)) : "" + (cal.get(Calendar.MINUTE));
        String ss = (cal.get(Calendar.SECOND) < 10) ? "0" + (cal.get(Calendar.SECOND)) : "" + (cal.get(Calendar.SECOND));

        String date = "" + year + "-" +(month < 10 ? "0" + month : month) + "-" +(day < 10 ? "0" + day : day)+ " " + hh + ":" + mm + ":" + ss;
        return date;
    }
    
    public static void main(String arg[]) {
    }

    
    public static String getParentDate1() {
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DAY_OF_MONTH, 1);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 2;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = "" + year + (month < 10 ? "0" + month : month) + (day < 10 ? "0" + day : day);
        return date;
    }
}
