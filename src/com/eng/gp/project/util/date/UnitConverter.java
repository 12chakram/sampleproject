package com.eng.gp.project.util.date;


/**
 * Dumping ground for various unit conversion constants and methods.
 */
public class UnitConverter {
    public static final double CM_PER_INCH = 2.54;
    public static final double INCH_PER_FOOT = 12;

    public static final double CM_PER_FOOT = CM_PER_INCH * INCH_PER_FOOT;
    public static final double FEET_PER_METER = 100 / CM_PER_FOOT;
    public static final double SQ_FEET_PER_SQ_METER = FEET_PER_METER * FEET_PER_METER;

    /**
     * Convert a value representing square meters to square feet.
     */
    public static double sqMetersToSqFeet(double sqMeters) {
        return sqMeters * SQ_FEET_PER_SQ_METER;
    }
}
