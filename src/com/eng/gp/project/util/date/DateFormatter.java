package com.eng.gp.project.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
public class DateFormatter {
    private static String DEFAULT_FORMAT = DateFormats.DATE_FORMAT_LOG;
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    public static String formatUtcUs(Date date, String nullResult) {
        return format(DEFAULT_FORMAT, date, UTC, Locale.US, nullResult);
    }

    public static String formatUtcUs(long date) {
        return format(DEFAULT_FORMAT, date, UTC, Locale.US);
    }

    public static String formatUtcUs(String formatStr, Date date, String nullResult) {
        return format(formatStr, date, UTC, Locale.US, nullResult);
    }

    public static String formatUtcUs(String formatStr, long date) {
        return format(formatStr, date, UTC, Locale.US);
    }

    public static String format(Date date, TimeZone timeZone, Locale locale, String nullResult) {
        return format(DEFAULT_FORMAT, date, timeZone, locale, nullResult);
    }

    public static String format(long date, TimeZone timeZone, Locale locale) {
        return format(DEFAULT_FORMAT, date, timeZone, locale);
    }

    public static String format(String formatStr, Date date, TimeZone timeZone, Locale locale, String nullResult) {
        if (null == date) {
            return nullResult;
        } else {
            return format(formatStr, date.getTime(), timeZone, locale);
        }
    }

    public static String format(String formatStr, long date, TimeZone timeZone, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr, locale);
        format.setTimeZone(timeZone);
        String result = format.format(date);
        return result;
    }

    public static Date parse(String formatStr, String date) throws ParseException {
        return (new SimpleDateFormat(formatStr).parse(date));
    }

    public static Date parse(String formatStr, String date, String timeZoneStr) throws ParseException {
        TimeZone tz = TimeZone.getTimeZone(timeZoneStr);
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        df.setTimeZone(tz);
        return (df.parse(date));
    }

}
