package com.eng.gp.project.util.date;

import java.util.TimeZone;

/**
 * <p>
 * Strategy to use when a wall clock time occurs twice in the requested {@link TimeZone}.
 * <p/>
 * 
 * <p>
 * In the US DST ended (in those TimeZones that observed it) on 2012-11-04 at 02:00
 * <p/>
 * 
 * <p>
 * Thus a local time of <code>2012-11-04 01:17</code> occurred twice in DST observing {@link TimeZone}s:
 * <ol>
 * <li>first during DST (GMT-0700)</li>
 * <li>then in Standard Time (GMT-0800).</li>
 * </ol>
 * <p/>
 * 
 * <p>
 * The strategy chosen will be used to resolve the correct instant for a US DST {@link TimeZone} from a
 * {@link LocalDateTime} of <code>2012-11-04 01:17</code>
 * <p/>
 */
public enum RepeatedHourResolver {
    /**
     * Default. Resolve ambiguous LocalDateTimes as Standard Time
     */
    PREFER_STANDARD,

    /**
     * Resolve ambiguous LocalDateTimes as Daylight Savings Time
     */
    PREFER_DST;
}
