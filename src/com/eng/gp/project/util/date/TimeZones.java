package com.eng.gp.project.util.date;

import java.util.TimeZone;

public class TimeZones {
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    public static final TimeZone US_PACIFIC = TimeZone.getTimeZone("US/Pacific");
    public static final TimeZone US_ARIZONA = TimeZone.getTimeZone("US/Arizona");
    public static final TimeZone US_CENTRAL = TimeZone.getTimeZone("US/Central");
    public static final TimeZone US_MOUNTAIN = TimeZone.getTimeZone("US/Mountain");
    public static final TimeZone US_EASTERN = TimeZone.getTimeZone("US/Eastern");
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final TimeZone ASIA_DUBAI = TimeZone.getTimeZone("Asia/Dubai");
    public static final TimeZone IST = TimeZone.getTimeZone("Asia/Calcutta"); // GMT+0530

    /**
     * Lord Howe Island is near Australia and has a half-hour DST shift
     * 
     * {@link http://en.wikipedia.org/wiki/Lord_Howe_Island}
     * 
     * <ul>
     * <li>In the Southern Hemisphere, DST starts in the fall.</li>
     * <li>DST BEGINS ON LORD HOWE ISLAND 2012-10-07 01:59:59.999 with a skip to 02:30:00.000</li>
     * <li>DST ENDS ON LORD HOWE ISLAND 2013-04-07 WHEN 01:30:00 to 01:59:59.999 IS REPEATED</li>
     * <li>Standard time is UTC +1030</li>
     * <li>Daylight Savings Time is UTC +1100</li>
     * </ul>
     */
    public static final TimeZone AUS_LORD_HOWE = TimeZone.getTimeZone("Australia/Lord_Howe");

    private TimeZones(){
        // DO NOT INSTANTIATE
    }
}
