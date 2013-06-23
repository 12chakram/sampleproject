package com.eng.gp.project.domain;

import java.util.Calendar;

/**
 * This is the Domain model object that is used to denote Time Series Sample Granularity.
 * Week is generally deprecated.
 * see the {@link com.gridpoint.energy.datamodel.timeseries.IntervalSize} for the dataModel version
 */
public enum Interval {
    ONE_MINUTE,
    FIVE_MINUTE,
    FIFTEEN_MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH;

    public void increment(Calendar input){
        switch (this) {
        case DAY:
            input.add(Calendar.DAY_OF_MONTH, 1);
            break;
        case HOUR:
             input.add(Calendar.HOUR_OF_DAY, 1);
             break;
        case FIFTEEN_MINUTE:
            input.add(Calendar.MINUTE, 15);
            break;
        case ONE_MINUTE:
            input.add(Calendar.MINUTE, 1);
            break;
        case FIVE_MINUTE:
            input.add(Calendar.MINUTE, 5);
            break;
        case MONTH:
            input.add(Calendar.MONTH, 1);
            break;
        case WEEK:
            input.add(Calendar.WEEK_OF_YEAR, 1);
            break;
        default: throw new IllegalArgumentException(this.name());
        }
    }

    /**
     * Gets the number of hours represented by an interval.
     * @param interval the interval of interest
     * @return the number of hours represented by {@code interval}
     */
    public static double getTimeFactor(Interval interval){
        switch(interval){
            case ONE_MINUTE:
                return (1D/60D);
            case FIVE_MINUTE:
                return (1D/12D);
            case FIFTEEN_MINUTE:
                return .25D;
            case HOUR:
                return 1D;
            case DAY:
                return 24D;
            case WEEK:
                return 168D;
            case MONTH:
                return 5040D;
            default:
                return 0D;
        }
    }

    /**
     * Gets the number of milliseconds represented by an interval.
     * @param interval the interval of interest
     * @return the number of milliseconds represented by {@code interval}
     */
    public static long getMillis(Interval interval){
        switch(interval){
            case ONE_MINUTE:
                return 60L*1000L;
            case FIVE_MINUTE:
                return 5L*60L*1000L;
            case FIFTEEN_MINUTE:
                return 15L*60L*1000L;
            case HOUR:
                return 60L*60L*1000L;
            case DAY:
                return 24L*60L*60L*1000L;
            case WEEK:
                return 7L*24L*60L*60L*1000L;
            case MONTH:
                return 30L*24L*60L*60L*1000L;
            default:
                return 0L;
        }
    }
}
