package com.eng.gp.project.util.date;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Time utilities.
 */
public class TimeUtil
{
    public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int HOURS_PER_DAY = 24;
    public static final int DAYS_PER_WEEK = 7;
    public static final int MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND;
    public static final int MILLISECONDS_PER_HOUR = MINUTES_PER_HOUR * MILLISECONDS_PER_MINUTE;
    public static final int MILLISECONDS_PER_DAY = HOURS_PER_DAY * MILLISECONDS_PER_HOUR;
    public static final int MILLISECONDS_PER_WEEK = DAYS_PER_WEEK * MILLISECONDS_PER_DAY;
    public static final int SECONDS_PER_HOUR = MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
    public static final int SECONDS_PER_DAY = HOURS_PER_DAY * SECONDS_PER_HOUR;

    private static final String formatStringLong = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL (UTC)";
    private static final String formatStringShort = "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL";
    private static final String formatStringFriendly = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS";

    /**
     * Format a UTC timestamp (in milliseconds since Jan 1 1970) in short form for display.
     *
     * @param timestamp The timestamp.
     * @return The formatted string.
     */
    public static String formatUtcTimestampShort( long timestamp )
    {
    return formatUtcTimestampHelper( timestamp, formatStringShort );
    }

    public static String formatUtcTimestampFriendly( long timestamp )
    {
    return formatUtcTimestampHelper( timestamp, formatStringFriendly );
    }

    /**
     * Format a UTC timestamp (in milliseconds since Jan 1 1970) from a Date object in short form for display.
     *
     * @param timestamp The timestamp.
     * @return The formatted string.
     */
    public static String formatUtcTimestampShort( Date timestamp )
    {
    return formatUtcTimestampShort( timestamp.getTime() );
    }

    /**
     * Format a UTC timestamp (in milliseconds since Jan 1 1970) for display.
     *
     * @param timestamp The timestamp.
     * @return The formatted string.
     */
    public static String formatUtcTimestamp( long timestamp )
    {
    return formatUtcTimestampHelper( timestamp, formatStringLong );
    }

    /**
     * Format a UTC timetamp (in milliseconds since Jan 1 1970) from a Date object for display.
     *
     * @param timestamp The timestamp.
     * @return The formatted string.
     */
    public static String formatUtcTimestamp( Date timestamp )
    {
    return formatUtcTimestamp( timestamp.getTime() );
    }

    /**
     * Format a UTC timestamp (in milliseconds since Jan 1 1970) for display.
     *
     * @param timestamp The timestamp.
     * @param format The format string.
     * @return The formatted string.
     */
    private static String formatUtcTimestampHelper( final long timestamp, final String format )
    {
    // get a UTC calendar (use the GMT timezone)
    Calendar utcCalendar = Calendar.getInstance();
    utcCalendar.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    utcCalendar.setTimeInMillis( timestamp );
    return String.format( format, utcCalendar );
    }

    /**
     * Format a calendar for display.
     *
     * @param calendar The calendar.
     * @return The formatted string.
     */
    public static String formatCalendar( Calendar calendar )
    {
    return String.format( formatStringLong, calendar );
    }

    /**
     * Calculate the beginning of the current time interval of a given length, if the first such interval
     * is aligned on the beginning of the current day (UTC).
     *
     * @param currentTime The current UTC time in milliseconds.
     * @param timeInterval The time interval duration in milliseconds.
     * @return The beginning time of the next interval.
     */
    public static long currentRoundIntervalStartTime( final long currentTime, final long timeInterval )
    {
        // get a UTC calendar (use the GMT timezone)
        Calendar utcCalendar = Calendar.getInstance();
        utcCalendar.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        utcCalendar.setTimeInMillis( currentTime );
        utcCalendar.set(Calendar.MILLISECOND, 0 );
        utcCalendar.set(Calendar.SECOND, 0 );
        utcCalendar.set(Calendar.MINUTE, 0 );
        utcCalendar.set(Calendar.HOUR_OF_DAY, 0 );
        long timeAtDayStart = utcCalendar.getTimeInMillis();
        long timeSinceDayStart = currentTime - timeAtDayStart;
        long timeSinceLastIntervalEnd = timeSinceDayStart % timeInterval;
        long currentIntervalStart = currentTime - timeSinceLastIntervalEnd;

        return currentIntervalStart;
    }

    /**
     * Return the time of the start of the day specified
     * by the given time.
     *
     * @param time the time to find the associated day.
     *
     * @return the time of the start of the day specified
     * by the given time.
     */
    public static long getStartOfDay(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTimeInMillis();
    }

    /**
     * @param date the date to find the associated day.
     * @return the date of the start of the day containting the specified date.
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    public static String yyyymmdd(long time) {
    Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
    calendar.setTimeInMillis(time);
//     calendar.set(Calendar.MILLISECOND,0);
//     calendar.set(Calendar.SECOND,0);
//     calendar.set(Calendar.MINUTE,0);
//     calendar.set(Calendar.HOUR_OF_DAY, 0);

        StringBuffer buffer = new StringBuffer();
        buffer.append(calendar.get(Calendar.YEAR));
        buffer.append(pad(calendar.get(Calendar.MONTH) + 1));
        buffer.append(pad(calendar.get(Calendar.DAY_OF_MONTH)));

        return buffer.toString();
    }

    public static String yyyymmddhhmm(long time) {
    Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTimeInMillis(time);

        StringBuffer buffer = new StringBuffer();
        buffer.append(calendar.get(Calendar.YEAR));
        buffer.append(pad(calendar.get(Calendar.MONTH) + 1));
        buffer.append(pad(calendar.get(Calendar.DAY_OF_MONTH)));
        buffer.append(pad(calendar.get(Calendar.HOUR_OF_DAY)));
        buffer.append(pad(calendar.get(Calendar.MINUTE)));

        return buffer.toString();
    }

    private static String pad(int i) {
        if (i >= 0 && i < 10) {
            return "0" + i;
        } else if (i >= 10 && i < 100) {
            return String.valueOf(i);
        } else {
            throw new IllegalArgumentException("argument (" + i + ") must be between 0 and 100 inclusive");
        }
    }

    /**
     * @param date the date to find the associated week.
     * @return the date of the start of the week containing the specified date.
     */
    public static Date getStartOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
        {
            calendar.add(Calendar.DAY_OF_YEAR,-1);
        }
        return calendar.getTime();
    }

    /**
     * @param date the date to find the associated month.
     * @return the date of the start of the month containing the specified date.
     */
    public static Date getStartOfMonth(Date date) {
        return getStartOfMonth(date, TimeZone.getTimeZone("UTC"));
    }
    
    public static Date getStartOfMonth(Date date, TimeZone timeZone) {
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timeZone);
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            return calendar.getTime();
        }
        return null;
    }

    /**
     * @param date the date to find the associated year.
     * @return the date of the start of the year containing the specified date.
     */
    public static Date getStartOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.MONTH,0);
        return calendar.getTime();
    }

    /**
     * Gets the time of the last millisecond of
     * the day specified by the given time.
     *
     * @param time the time used to find the
     * associated day.
     *
     * @return the time of the last millisecond
     * of the day specified by the given time.
     */
    public static long getEndOfDay(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        return calendar.getTimeInMillis();
    }

     /**
     * Gets the date of the last millisecond of
     * the day specified by the given time.
     *
     * @param date the date used to find the
     * associated day.
     *
     * @return the date of the last millisecond
     * of the day specified by the given date.
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        return calendar.getTime();
    }

    /**
     * Returns the number of milliseconds since the beginning of
     * the day, for the given time.
     *
     * @param time the time to calculate the millisecond offset for,
     * in milliseconds since Jan. 1, 1970
     *
     * @return the number of millis since the beginning of the day,
     * specified by the time parameter.
     */
    public static long getMillisOfDay(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(time);

        return calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000 +
            calendar.get(Calendar.MINUTE) * 60 * 1000 +
            calendar.get(Calendar.SECOND) * 1000 +
            calendar.get(Calendar.MILLISECOND);
    }

    /**
     * Returns true if the <i>start</i>, <i>end</i> range overlap entirely
     * or partially with the given <i>year</i>, <i>month</i> pair.
     *
     * @param start the start date.
     * @param end the end date.
     * @param year the year to test against.
     * @param month the month to test against.
     *
     * @return true if there is a complete or partial overlap of dates.
     */
    public static boolean overlap(Date start, Date end, int year, int month) {
        Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c1.setTime(start);

        Calendar c2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c2.setTime(end);

        if (c1.get(Calendar.YEAR) > year ||
            c2.get(Calendar.YEAR) < year) {
            return false;
        } else if (c1.get(Calendar.YEAR) < year) {
            return ((c2.get(Calendar.YEAR) > year) ||
                ((c2.get(Calendar.MONTH) + 1) >= month));
        } else {
            return ((c1.get(Calendar.MONTH) + 1) <= month &&
                (c2.get(Calendar.MONTH) + 1) >= month);
        }
    }

    /**
     * Returns true if the range specified by <i>start</i>, <i>end</i>
     * spans the given <i>date</i>.
     *
     * @param start the start of the range.
     * @param end the end of the range.
     * @param date the date to test the span against.
     *
     * @return true if the range spans the date, false otherwise.
     */
    public static boolean overlap(Date start, Date end, Date date) {
    return (start.compareTo(date) <= 0 && end.compareTo(date) >= 0);
    }

    /**
     * @param in1 a date to compare
     * @param in2 a date to compare
     * @return returns zero if equal, negative if in1 before in2, positive if in1 after in2
     */
    public static int compare(Date in1, Date in2)
    {
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar1.setTime(in1);
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar2.setTime(in2);
        return calendar1.compareTo(calendar2);
    }

    /**
     * @param in a date to which to add time
     * @param field the unit of time
     * @param value the number of units to add
     * @return returns in plus value times field
     */
    public static Date add(Date in, int field, int value)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(in);
        calendar.add(field,value);
        return calendar.getTime();
    }

    /**
     * @param in a date from which to subtract time
     * @param field the unit of time
     * @param value the number of units to subtract
     * @return returns in minus value times field
     */
    public static Date subtract(Date in, int field, int value)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(in);
        calendar.add(field,-1*value);
        return calendar.getTime();
    }

    /**
     * Returns a predetermined time zone for a given time zone
     * offset in minutes. This timezone WILL adjust for DST.
     * Not all offsets have been mapped to a given time zone yet,
     * the following are available:
     * <ul>
     * <li>UTC</li>
     * <li>Canada/Atlantic</li>
     * <li>US/Eastern</li>
     * <li>US/Central</li>
     * <li>US/Mountain</li>
     * <li>US/Pacific</li>
     * <li>US/Pacific</li>
     * </ul>
     *
     * @param offset time zone offset from GMT in minutes.
     */
    public static TimeZone getDstEnabledTimeZone(int offset) {
        switch(offset / 60) {
        case -0:
            return TimeZone.getTimeZone("Europe/London");
        case -4:
            return TimeZone.getTimeZone("Canada/Atlantic");
        case -5:
            return TimeZone.getTimeZone("US/Eastern");
        case -6:
            return TimeZone.getTimeZone("US/Central");
        case -7:
            return TimeZone.getTimeZone("US/Mountain");
        case -8:
            return TimeZone.getTimeZone("US/Pacific");
        case -10:
            return TimeZone.getTimeZone("US/Hawaii");
        default:
            throw new IllegalArgumentException("unknown time zone: " + offset);
        }
    }


    /**
     * Get the TimeZone object that is a pure GMT offset, and doesn't adjust for DST.
     * Doesn't allow partial GMT offsets.
     * http://wwp.greenwichmeantime.com/info/timezone.htm
     * @param minuteOffset time zone offset from GMT in minutes.
     */
    public static TimeZone getNoDstTimeZone( final int minuteOffset ) {
        assert minuteOffset % 60 == 0 : "Weird partial offset " + minuteOffset + " partial:" + ( minuteOffset % 60 );
        int convertedOffset = minuteOffset / 60;

        assert convertedOffset >= -12 &&  convertedOffset <= 14 : "invlaid offset " + convertedOffset;

        String tzStr = "";
        if( convertedOffset > 0 ) {
            tzStr = "GMT+" + convertedOffset;
        } else if( convertedOffset < 0 ) {
            tzStr = "GMT" + convertedOffset;
        }else {
            tzStr = "GMT+" + convertedOffset;
        }
        return( TimeZone.getTimeZone(tzStr) );
    }

    /**
     * Utility method to construct a date with the given time and time zone offest.
     *
     * @param year 4 digit year
     * @param month 1-12
     * @param day 1-x
     * @param hour 0-23
     * @param minute 0-59
     * @param second 0-59
     * @param timeZone time zone offset in minutes.
     *
     * @return a Calendar object initialized with the given parameters.
     */
    public static Calendar createDateNoDst(int year, int month, int day, int hour, int minute, int second, int timeZone) {
        TimeZone zone = getNoDstTimeZone(timeZone);
        Calendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.setTimeZone(zone);
        calendar.set(year, month - 1, day, hour, minute, second);

        return calendar;
    }

    // Convert Time in millis to a new time zone
    public static long getConvertTimeInMillis(long baseTime, TimeZone fromTimeZone, TimeZone toTimeZone) {
        // If the one of given timezones isn't available, don't mess with the times
        if ((fromTimeZone == null) || (toTimeZone == null) || fromTimeZone.getID().equals(toTimeZone.getID())) {
            return baseTime;
        }

        // So... I extend the GregorianCalendar to allow myself to change the timezone and
        // propagate the change to to the millisecond field rather than propagating the change to the fields.
        Calendar localCal = new GregorianCalendar(fromTimeZone) {
            private static final long serialVersionUID = 1L;
            public void setTimeZone (TimeZone zone) {
                computeFields();
                super.setTimeZone(zone);
                computeTime();
            }
        };
        localCal.setTimeInMillis(baseTime);
        localCal.setTimeZone(toTimeZone);
        return localCal.getTimeInMillis();
    }

    // Checks if the default is returned and if so returns a given defaule
    public static TimeZone getTimeZoneWithDefault(String timeZoneName, TimeZone defaultTimeZone) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);
        if (timeZone.getID().equals("GMT") && !timeZoneName.equals("GMT")) {
            return defaultTimeZone;
        }
        return timeZone;
    }

    /**
     * Return null if no timezone.
     * @param timeZoneName
     * @return
     */
    public static TimeZone getTimeZoneStrict (String timeZoneName) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);
        if (timeZone.getID().equals("GMT") && !timeZoneName.equals("GMT")) {
            return( null );
        }
        return timeZone;
    }

    public static long truncToDay (long time, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTimeInMillis();
    }

    public static long truncToMonth (long time, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTimeInMillis();
    }

    public static long numberOfDays (Long start, Long end) {
        return numberOfDays(start, end, Calendar.getInstance().getTimeZone());
    }

    public static long numberOfDays (Long start, Long end, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(start);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        long days = 0;
        while (calendar.getTimeInMillis() < end) {
            days++;
            calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

    /**
     * Change a date in some local TimeZone to UTC
     * This handles daylight savings time.
     * @param date
     * @param timeZone
     * @return
     */
    public static Date toUTC(Date date, TimeZone timeZone) {
        Calendar cal = GregorianCalendar.getInstance(timeZone);
        cal.setTimeInMillis(date.getTime());
        Date utcTime = new Date(date.getTime() - cal.get(Calendar.ZONE_OFFSET) - cal.get(Calendar.DST_OFFSET));
        return utcTime;
    }

    /**
     * Change the time in some local TimeZone to UTC
     * This handles daylight savings time.
     * @param time
     * @param timeZone
     * @return
     */
    public static long toUTC(long time, TimeZone timeZone) {
        return toUTC(new Date(time), timeZone).getTime();
    }

    public static Date fromUTC(Date date, TimeZone timeZone){
        Calendar cal = GregorianCalendar.getInstance(timeZone);
        cal.setTimeInMillis(date.getTime());
        Date localTime = new Date(date.getTime() + cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET));
        return localTime;
    }

    public static long fromUTC(long time, TimeZone timeZone) {
        return fromUTC(new Date(time), timeZone).getTime();
    }

    public static long getUtcDay(long firstTime, TimeZone timeZone) {
        TimeZone utcTimeZone = TimeZone.getTimeZone("GMT");
        long utcFirstTime = TimeUtil.getConvertTimeInMillis(firstTime, timeZone, utcTimeZone);
        return TimeUtil.truncToDay(utcFirstTime, utcTimeZone);
    }

    public static long add(long in, int field, int value)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(in);
        calendar.add(field,value);
        return calendar.getTimeInMillis();
    }

    public static long subtract(long in, int field, int value)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(in);
        calendar.add(field,-1*value);
        return calendar.getTimeInMillis();
    }

    public static int getDaysAgo(long eventTime, long zeroDayTime) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(zeroDayTime);
        calendar.set(Calendar.MILLISECOND,999);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        zeroDayTime = calendar.getTimeInMillis();

        return ( (int) ( ( zeroDayTime - eventTime ) / ( 24 * 60 * 60 * 1000L ) ) );
    }

    /**
     * Gets a calendar in the UTC time zone.
     * This is frequently used to prevent time zone issues with database queries.
     * @param date the date to set the calendar to.
     * @return a calendar in the UTC time zone set to {@code date}.
     */
    public static Calendar getUtcCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Converts a long (duration) into a human readable format ( (x d) hh:mm:ss )
     *
     * i.e. 2d 05:25:42 (2 days, 5 hours, 25 minutes and 42 seconds)
     */
    public static String millisToDisplayDate(long duration) {
        String res = "";
        duration /= MILLISECONDS_PER_SECOND;
        int seconds = (int) (duration % SECONDS_PER_MINUTE);
        duration /= SECONDS_PER_MINUTE;
        int minutes = (int) (duration % MINUTES_PER_HOUR);
        duration /= 60;
        int hours = (int) (duration % HOURS_PER_DAY);
        int days = (int) (duration / HOURS_PER_DAY);
        if (days == 0) {
          res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
          res = String.format("%dd %02d:%02d:%02d", days, hours, minutes, seconds);
        }
        return res;
      }
    
    /**
     * Returns days between 2 long times, adjusting for DST values as appropriate based on the TimeZone passed in,
     * or UTC if timezone is null
     * @param start
     * @param end
     * @param zone
     * @return
     */
    public static long daysBetween(Long start, Long end, TimeZone zone) {
        if(zone == null) {
            zone = TimeZone.getTimeZone("UTC");
        }
        
        Calendar d1 = Calendar.getInstance();
        d1.setTimeZone(zone);
        d1.setTimeInMillis(start);
        
        Calendar d2 = Calendar.getInstance();
        d2.setTimeZone(zone);
        d2.setTimeInMillis(end);
        
        Long adjustedStart = d1.getTimeInMillis() + d1.get(Calendar.DST_OFFSET);
        Long adjustedEnd = d2.getTimeInMillis() + d2.get(Calendar.DST_OFFSET);
        
        return (adjustedEnd - adjustedStart) / TimeUtil.MILLISECONDS_PER_DAY;
    }
}
