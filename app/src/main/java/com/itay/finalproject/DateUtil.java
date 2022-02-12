package com.itay.finalproject;

import java.util.Calendar;

public class DateUtil {
    private DateUtil() { }

    public static String getDateString(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }
}
