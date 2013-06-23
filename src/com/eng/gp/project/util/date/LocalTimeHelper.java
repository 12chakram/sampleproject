package com.eng.gp.project.util.date;

import org.joda.time.LocalTime;

/**
 * @author dhorlick
 */
public class LocalTimeHelper
{
    /**
     * Generates a time string in the modified, padded format HH:MM.
     *
     * For closing times, 12 o'clock AM will be represented as 24:00, not 0:00.
     */
    public static String formatLocalTime(final LocalTime localTime, final boolean timeIsClosingTime)
    {
        if (localTime==null)
            return null;
        if (timeIsClosingTime && localTime.getHourOfDay()==0 && localTime.getMinuteOfHour()==0)
            return "24:00";
        else
            return formatPadded(localTime.getHourOfDay())+":"+ formatPadded(localTime.getMinuteOfHour());
    }

    public static String formatPadded(final int minutes)
    {
        final StringBuilder stringBuilder = new StringBuilder();

        if (minutes<10)
            stringBuilder.append("0");

        stringBuilder.append(minutes);

        return stringBuilder.toString();
    }

    public static LocalTime parseLocalTime(final String timeString)
    {
        if (timeString==null)
            return null;

        final String [] parts = timeString.split(":");

        if (parts.length==1)
        {
            final int hoursPart = Integer.parseInt(parts[0]);
            return new LocalTime(hoursPart % 24, 0);
        }
        else if (parts.length>2)
        {
            throw new IllegalArgumentException("Invalid number of parts in time string "+timeString);
        }

        final int hoursPart = Integer.parseInt(parts[0]);
        final int minutesPart = Integer.parseInt(parts[1]);

        return new LocalTime(hoursPart % 24, minutesPart);
    }
}
