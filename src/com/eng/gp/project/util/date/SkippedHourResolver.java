package com.eng.gp.project.util.date;

import java.util.TimeZone;


/**
 * <p>
 * Strategy to use when a wall clock time does not occur in the requested TimeZone.
 * </p>
 * 
 * <p>
 * In the US DST began (in those TimeZones that observed it) on 2012-03-11 at 03:00:00
 * </p>
 * 
 * <p>
 * The local time <code>2012-03-11 02:17</code> never occurred in DST observing {@link TimeZone}.
 * 
 * Time in those locations  proceeded from:
 * <ol>
 * <li>2012-03-11 01:59:59.999 to</li>
 * <li>2012-03-11 03:00:00.000</li>
 * </ol>
 * </p>
 * 
 * <p>
 * The strategy chosen will be used if necessary to resolve these phantom times into a local time that occurred
 * <p/>
 */
public enum SkippedHourResolver {
    /**
     * Default. Resolve the situation by incrementing the time by the DST offset.
     * 
     * <p>
     * <code>2012-03-11 02:17</code> will be resolved to <code>2012-03-11 03:17</code> in "US/Pacific"
     * </p>
     */
    ADD_DST_OFFSET,

    /**
     * Resolve the situation by throwing in instance of {@link SkippedHourException}
     */
    THROW_SKIPPED_HOUR_EXCEPTION,

    /**
     * Resolve the situation by incrementing the time to the beginning of DST.
     * 
     * <p>
     * <code>2012-03-11 02:17</code> will be resolved to <code>2012-03-11 03:00</code> in "US/Pacific"
     * </p>
     */
    NEXT_AVAILABLE;

}
