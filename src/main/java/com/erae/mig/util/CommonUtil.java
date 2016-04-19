package com.erae.mig.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    public static boolean isNull(String obj) {
        if (obj == null || obj.equals("") || obj.equals("null") || obj.equals("undefined")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Object obj) {
        if (obj == null || obj.equals("") || obj.equals("null") || obj.equals("undefined")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean equals(Object obj, Object val) {
        if (obj != null && !obj.equals("") && !obj.equals("null") && !obj.equals("undefined") && val(obj).equals(val(val))) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String val(Object obj) {
    	String result = obj + "";
        return result.trim();
    }
    
    public static boolean isDate(String dateString) {

    	boolean result = true;
    	try {
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    Date to = sdf.parse(dateString);
    	    result = true;
        }catch (Exception e) {
        	e.printStackTrace();
        	result = false;
        }
    	return result;
    }
}
