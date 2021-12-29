package org.secureauth.sarestapi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {

    public static String getServerTime( Boolean oldIdpMode ) {
        if (oldIdpMode) return getServerTimeSeconds();
        return getServerTimeMs();
    }

    public static String getServerTimeMs() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss.SSS z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    //Just in case we want to force the second version timestamp for older versions of IDP
    public static String getServerTimeSeconds() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}
