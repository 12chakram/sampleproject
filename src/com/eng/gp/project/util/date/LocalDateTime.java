package com.eng.gp.project.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.HashCodeBuilder;


import static com.eng.gp.project.util.date.TimeZones.UTC;

/**
 * Represents date and time on the wall. Similar to JodaTime's LocalDateTime class, but with optional behavior to
 * resolve ambiguous or non-existent timeZone translations.
 */
public class LocalDateTime implements Comparable<LocalDateTime>{
    private static final Pattern[] REG_EX_PATTERNS = new Pattern[] {//
    Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})[T|\\s](\\d{2})\\:(\\d{2})\\:(\\d{2})\\.(\\d{1,3})"), // MILLIS_PATTERN
            Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})[T|\\s](\\d{2})\\:(\\d{2})\\:(\\d{2})"), // SECONDS_PATTERN
            Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})[T|\\s](\\d{2})\\:(\\d{2})"), // MINUTES_PATTERN
            Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})") // DATE_PATTERN
    };
    private static final String OUTPUT_FMT_STRING = DateFormats.DATE_FORMAT_LOCALDATETIME;
    private static final long MILLIS_IN_SECOND = 1000;
    private static final long MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;
    private static final int[] CALENDAR_FIELDS = new int[] { //
    Calendar.YEAR, //
            Calendar.MONTH, //
            Calendar.DAY_OF_MONTH, //
            Calendar.HOUR_OF_DAY, //
            Calendar.MINUTE, //
            Calendar.SECOND, //
            Calendar.MILLISECOND //
    };

    private final String string;
    private final long utcInstant; // The point in time when a clock in UTC reads the same as the LocalDateTime
                                   // represented by this class

    /**
     * Returns the LocalDateTime resulting from interpreting the given date as an instant in UTC.
     * @param utcDate
     * @return the LocalDateTime or null if utcDate was null
     */
    public static LocalDateTime forUtc(Date utcDate) {
        if(null == utcDate){
            return null;
        }
        LocalDateTime result = new LocalDateTime(utcDate.getTime(), UTC);
        return result;
    }

    public static LocalDateTime forUtc(long utcInstant) {
        LocalDateTime result = new LocalDateTime(utcInstant, UTC);
        return result;
    }

    /**
     * Parses a string in one of the following forms into a LocalDateTime
     * <ul>
     * <li>2012-02-03T04:05:06.789</li>
     * <li>2012-02-03 04:05:06.789</li>
     * <li>2012-02-03T04:05:06</li>
     * <li>2012-02-03 04:05:06</li>
     * <li>2012-02-03T04:05</li>
     * <li>2012-02-03 04:05</li>
     * <li>2012-02-03</li>
     * </ul>
     * 
     * @param input
     *            - formatted String
     * @return LocalDateTime
     * @throws IllegalArgumentException
     *             for unparsable input or bad values
     */
    public static LocalDateTime valueOf(String input) {
        for (Pattern pattern : REG_EX_PATTERNS) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String yearStr = matcher.group(1);
                int year = parseInt(yearStr, input, "year", null);
                String month1to12Str = matcher.group(2);
                int month1to12 = parseInt(month1to12Str, input, "month1to12", null);
                String dayStr = matcher.group(3);
                int day = parseInt(dayStr, input, "day", null);

                int hour24 = 0, minute = 0, second = 0, millis = 0;

                if (4 <= matcher.groupCount()) {
                    String hour24Str = matcher.group(4);
                    hour24 = parseInt(hour24Str, input, "hour24", 0);
                }
                if (5 <= matcher.groupCount()) {
                    String minuteStr = matcher.group(5);
                    minute = parseInt(minuteStr, input, "minute", 0);
                }
                if (6 <= matcher.groupCount()) {
                    String secondStr = matcher.group(6);
                    second = parseInt(secondStr, input, "second", 0);
                }
                if (7 <= matcher.groupCount()) {
                    String millisStr = matcher.group(7);
                    millis = parseInt(millisStr, input, "millis", 0);
                }
                LocalDateTime result = new LocalDateTime(year, month1to12, day, hour24, minute, second, millis);
                return result;
            }
        }
        throw new IllegalArgumentException("Unable to parse \"" + input + "\"");
    }

    private static int parseInt(String input, String fullString, String fieldName, Integer defaultValue) {
        if (null == input || "".equals(input)) {
            if (null != defaultValue) {
                return defaultValue;
            }
            throw new IllegalArgumentException("Unable to parse fieldName as " + input + " from " + fullString);
        }
        try {
            int result = Integer.parseInt(input);
            return result;
        } catch (NumberFormatException ex) {
            if (null != defaultValue) {
                return defaultValue;
            }
            throw new IllegalArgumentException("Unable to parse fieldName as " + input + " from " + fullString);
        }
    }

    /**
     * Construct a LocalDateTime according to the rules of the indicated TimeZone and the specified instant.
     * 
     * @param instant
     * @param timeZone
     */
    public LocalDateTime(long instant, TimeZone timeZone) {
        long offset = timeZone.getOffset(instant);
        utcInstant = instant + offset;
        string = buildString(utcInstant);
    }

    public LocalDateTime(int year, int month1to12, int day) {
        this(year, month1to12, day, 0, 0, 0, 0);
    }

    public LocalDateTime(int year, int month1to12, int day, int hour24, int minute) {
        this(year, month1to12, day, hour24, minute, 0, 0);
    }

    public LocalDateTime(int year, int month1to12, int day, int hour24, int minute, int second) {
        this(year, month1to12, day, hour24, minute, second, 0);
    }

    public LocalDateTime(int year, int month1to12, int day, int hour24, int minute, int second, int millisecond) {
        if (month1to12 < 1 || 13 <= month1to12) {
            throw new IllegalArgumentException("month must be (1 or greater) and (12 or less), was " + month1to12);
        }
        if (day < 1 || 32 <= day) {
            throw new IllegalArgumentException("day must be (1 or greater) and (31 or less), was " + day);
        }
        if (hour24 < 0 || 24 <= hour24) {
            throw new IllegalArgumentException("hour24 must be (0 or greater) and (less than 24), was " + hour24);
        }
        if (minute < 0 || 60 <= minute) {
            throw new IllegalArgumentException("minute must be (0 or greater) and (less than 60), was " + minute);
        }
        if (second < 0 || 60 <= second) {
            throw new IllegalArgumentException("second must be (0 or greater) and (less then 60), was " + second);
        }
        if (millisecond < 0 || 1000 <= millisecond) {
            throw new IllegalArgumentException("millisecond must be (0 or greater) and (less than 1000), was " + millisecond);
        }
        Calendar utcCalendar = Calendar.getInstance(UTC);
        utcCalendar.clear();
        utcCalendar.setLenient(false);
        utcCalendar.set(Calendar.YEAR, year);
        utcCalendar.set(Calendar.MONTH, month1to12 - 1); // Calendar months are indexed from 0-11
        utcCalendar.set(Calendar.DAY_OF_MONTH, day);
        utcCalendar.set(Calendar.HOUR_OF_DAY, hour24);
        utcCalendar.set(Calendar.MINUTE, minute);
        utcCalendar.set(Calendar.SECOND, second);
        utcCalendar.set(Calendar.MILLISECOND, millisecond);

        this.utcInstant = utcCalendar.getTimeInMillis();
        this.string = buildString(utcInstant);
    }

    @Override
    public int compareTo(LocalDateTime o) {
        if(this.utcInstant == o.utcInstant){
            return 0;

        } else if((this.utcInstant - o.utcInstant) < 0){
            return -1;

        } else {
            return 1;
        }
    }

    /**
     * Returns a new LocalDateTime incremented by the specified number of Milliseconds.
     */
    public LocalDateTime add(long millis) {
        return new LocalDateTime(utcInstant + millis, UTC);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(utcInstant).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LocalDateTime)) {
            return false;
        }
        LocalDateTime that = (LocalDateTime) obj;
        boolean isEqual = this.utcInstant == that.utcInstant;
        return isEqual;
    }

    @Override
    public String toString() {
        return string;
    }

    private static String buildString(long utcInstant) {
        SimpleDateFormat sdf = new SimpleDateFormat(OUTPUT_FMT_STRING);
        sdf.setTimeZone(UTC);
        String result = sdf.format(new Date(utcInstant));
        return result;
    }

    /**
     * Returns the instant when a clock in UTC would read the same this LocalDateTime
     */
    public long instantInUtc() {
        return utcInstant;
    }

    /**
     * Returns the instant when a clock in the <code>destTz</code> {@link TimeZone} would have had/will have the same
     * value as this {@link LocalDateTime}
     * 
     * Use {@link SkippedHourResolver.ADD_DST_OFFSET} and {@link RepeatedHourResolver.PREFER_STANDARD} to resolve
     * ambiguous localTimes
     * 
     * @param destTz
     * @return - the instant when a Clock in destTz would correspond to this LocalDateTime
     */
    public long instantInTz(TimeZone destTz) {
        long result = instantInTz(this, destTz, SkippedHourResolver.ADD_DST_OFFSET, RepeatedHourResolver.PREFER_STANDARD);
        return result;
    }

    /**
     * Returns the instant when a clock in the <code>destTz</code> {@link TimeZone} would have had/will have the same
     * value as this {@link LocalDateTime}
     * 
     * Use the provided {@link SkippedHourResolver} and {@link RepeatedHourResolver} to resolve ambiguous localTimes
     * 
     * @return - the instant when a clock in <code>destTz</code> would have the same value as <code>source</code>
     */
    public long instantInTz(TimeZone destTz, SkippedHourResolver skipResolver, RepeatedHourResolver repeatResolver) {
        long result = instantInTz(this, destTz, skipResolver, repeatResolver);
        return result;
    }

    /**
     * Returns the instant when a clock in the <code>destTz</code> {@link TimeZone} would have had/will have the same
     * value as <code>source</code>
     * 
     * Use the provided {@link SkippedHourResolver} and {@link RepeatedHourResolver} to resolve ambiguous localTimes
     * 
     * @return - the instant when a clock in <code>destTz</code> would have the same value as <code>source</code>
     */
    private static long instantInTz(LocalDateTime source, TimeZone destTz, SkippedHourResolver skipResolver, RepeatedHourResolver repeatResolver) {
        long result = 0;
        if (UTC.hasSameRules(destTz)) {
            return source.instantInUtc();

            /*
             * I WOULD LIKE TO CHECK destTz.useDaylightTime() HERE AND SIMPLY APPLY THE DST OFFSET IF THE ANSWER WAS
             * FALSE, BUT THE METHOD ON TimeZone DOESN'T REFLECT CHANGES TO DST OVER TIME
             */

        } else {

            // Construct a calendar to represent the value of source
            Calendar sourceCal = Calendar.getInstance(UTC);
            sourceCal.clear();
            sourceCal.setTimeInMillis(source.instantInUtc());

            // Construct a calendar to represent the value of source in the destination TimeZone
            Calendar destCal = copyFields(sourceCal, destTz);
            long destInstant = destCal.getTimeInMillis();

            // Get the time on the wall in the destTz
            LocalDateTime destInstantWallTime = new LocalDateTime(destInstant, destTz);
            result = destInstant;

            // Check if we found a corresponding wall time in the destTz
            if (source.equals(destInstantWallTime)) {

                // Check to see if the current wall time occurred more than once in the destTz
                Calendar destPlusOffsetCal = (Calendar) destCal.clone();
                destPlusOffsetCal.add(Calendar.MILLISECOND, destTz.getDSTSavings());
                boolean inFirstHourOfFallbackRepeat = compareFields(destCal, destPlusOffsetCal);

                Calendar destMinusOffsetCal = null;
                boolean inSecondHourOfFallbackRepeat = false;

                // Try and cut down on some unnecessary Calendar work
                if (!inFirstHourOfFallbackRepeat) {
                    destMinusOffsetCal = (Calendar) destCal.clone();
                    destMinusOffsetCal.add(Calendar.MILLISECOND, -1 * destTz.getDSTSavings());
                    inSecondHourOfFallbackRepeat = compareFields(destCal, destMinusOffsetCal);
                }

                // If the wall time occurred more than once in the destTz, we are in a DST ending fallback period
                if (inFirstHourOfFallbackRepeat || inSecondHourOfFallbackRepeat) {

                    // Depending on whether destCal has placed us in the 1st or 2nd fallback hour and whether we want
                    // the 1st or 2nd hour, get the correct value
                    if ((RepeatedHourResolver.PREFER_DST == repeatResolver) && (inFirstHourOfFallbackRepeat)) {
                        result = destCal.getTimeInMillis();

                    } else if ((RepeatedHourResolver.PREFER_DST == repeatResolver) && (inSecondHourOfFallbackRepeat)) {
                        result = destMinusOffsetCal.getTimeInMillis();

                    } else if (inFirstHourOfFallbackRepeat) { // RepeatedHourResolver.PREFER_STANDARD
                        result = destPlusOffsetCal.getTimeInMillis();

                    } else { // RepeatedHourResolver.PREFER_STANDARD && insecondHourOfFallbackRepeat
                        result = destCal.getTimeInMillis();
                    }
                }

                // Otherwise, source doesn't occur in destTz and must be during the DST skip Time
            } else {

                if(SkippedHourResolver.THROW_SKIPPED_HOUR_EXCEPTION == skipResolver) {
                    throw new SkippedHourException();

                } else if (SkippedHourResolver.ADD_DST_OFFSET == skipResolver) {
                    // NO FURTHER WORK TO DO, CALENDAR ALREADY INCREMENTS BY DST SHIFT

                } else { // (SkippedHourResolver.NEXT_AVAILABLE == springForward)
                    // While our calendar is in DST, keep subtracting time to eventually get to the first moment of DST
                    // Start subtracting with bigger chunks to speed things along
                    long[] increments = new long[] { //
                    10 * MILLIS_IN_MINUTE, //
                            MILLIS_IN_MINUTE, //
                            10 * MILLIS_IN_SECOND, //
                            MILLIS_IN_SECOND, //
                            100, //
                            10, //
                            1 };

                    for (int incrementCtr = 0; incrementCtr < increments.length; incrementCtr++) {
                        long increment = increments[incrementCtr];
                        Date incrementBeforeDest = new Date(destCal.getTimeInMillis() - increment);

                        while (destTz.inDaylightTime(incrementBeforeDest)) {
                            destCal.setTimeInMillis(incrementBeforeDest.getTime());
                            incrementBeforeDest = new Date(destCal.getTimeInMillis() - increment);
                        }
                    }
                    result = destCal.getTimeInMillis();
                }
            }
        }
        return result;
    }

    private static boolean compareFields(Calendar left, Calendar right) {
        for (int field : CALENDAR_FIELDS) {
            if (left.get(field) != right.get(field)) {
                return false;
            }
        }
        return true;
    }

    private static Calendar copyFields(Calendar sourceCal, TimeZone destTz) {
        Calendar destCal = Calendar.getInstance(destTz);
        destCal.clear();

        for (int field : CALENDAR_FIELDS) {
            destCal.set(field, sourceCal.get(field));
        }
        return destCal;
    }
}
