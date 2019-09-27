package com.zthl.nxp.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

    static Calendar calendars;
    public static String getCurrentTime(){

        calendars = Calendar.getInstance();

        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));


        String year = String.valueOf(calendars.get(Calendar.YEAR));

        String month = String.valueOf(calendars.get(Calendar.MONTH));

        String day = String.valueOf(calendars.get(Calendar.DATE));

        String hour = String.valueOf(calendars.get(Calendar.HOUR));

        String min = String.valueOf(calendars.get(Calendar.MINUTE));

        String second = String.valueOf(calendars.get(Calendar.SECOND));


return  year+" 年"+month+" 月 "+day+" 日";


    }
}
