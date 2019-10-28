package com.randomappsinc.travelbuddy.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    private static final String DATE_FORMAT = "EEEE, MMMM d, yyyy - h:mm a";
    private static final String FEED_DATE_FORMAT = "MM/d/yy - h:mm a";

    public static String getDefaultTimeText(long unixTime, TimeZone timeZone) {
        Date date = new Date(unixTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    public static String getFeedTimeText(long unixTime, TimeZone timeZone) {
        Date date = new Date(unixTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FEED_DATE_FORMAT, Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }
}
