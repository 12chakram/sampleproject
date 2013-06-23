package com.eng.gp.project.domain;

/**
 * The IntervalSize class is used to denote various granularities of time series interval data.
 * many intervals are possible, but the currently supported interval sizes are one,five,fifteen minutes.
 * Hourly, Daily ( a specific timezone must be specified), and Monthly (always UTC Month, not time zone specific).
 * Week is only left in for legacy purposes and is generall deprecated.
 * This class can also convert to/from the Interval domain model version of this class
 * see {@link com.gridpoint.energy.domainmodel.Interval} for the domain model version
 */
public enum IntervalSize
{
    //ONE_SECOND((byte)1,1),
    //FIVE_SECOND((byte)2,5),
    //FIFTEEN_SECOND((byte)3,15),
    //THIRTY_SECOND((byte)4,30),
    ONE_MINUTE((byte)5,60),
    FIVE_MINUTE((byte)6,300),
    FIFTEEN_MINUTE((byte)7,900),
    //THIRTY_MINUTE((byte)8,1800),
    HOUR((byte)9,3600),
    DAY((byte)10,86400),
    WEEK((byte)11,604800),
    MONTH((byte)12, 2419200),
    UNKNOWN((byte)-1, -1);

    private final int   val;
    private final byte  byteId;

    private IntervalSize (byte byteVal, int val) {
        this.val=val;
        this.byteId = byteVal;
    }

    public byte getByteId () {
        return byteId;
    }

    public int compare( IntervalSize other ) {
        if( this.val > other.val ) {
            return(1);
        }
        else if( this.val < other.val ) {
            return(-1);
        }
        else
            return(0);
    }

    public IntervalSize max(IntervalSize other){
        return this.compare(other) < 0 ? other : this;
    }

    /**
     * generally the number of seconds.. But don't use it to calculate time.. because of DST/LeapYear..etc.etc
     * @return
     */
    public int getVal () {
        return val;
    }
    public long getIntervalLengthMillis() {
        if( val <= HOUR.val ) {
            return( val * 1000 );
        }
        else {
            //Don't use this method for Day/Week/Month
            throw new RuntimeException("Unknown Interval Length (DST/Leap Year etc) " + this );
        }
    }

    /**
     * Interval Length in Minutes... I change the naming convention because Millis and Minutes are too similar
     * and I keep using the wrong one.
     * @return
     */
    public int getMinuteIntervalLength () {
        return( (int)(getIntervalLengthMillis() / 60000) );
    }
    
    /**
     * Find out if this is a regular interval. Useful in the cases of endpoints, where if they're posting their 
     * very first interval message, it may or may not be a regular interval. In these cases, we'll want to discard
     * this partial message. 
     * @param intervalMillis
     * @return
     */
    public static boolean isRegularInterval(long intervalMillis) {
        try {
            fromMillis(intervalMillis);
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }


    public static IntervalSize fromMillis (long intervalMillis ) {
        switch( (int)intervalMillis ) {
//            case(1000):
//                return( IntervalSize.ONE_SECOND );
//            case(5000):
//                return( IntervalSize.FIVE_SECOND );
//            case(15000):
//                return( IntervalSize.FIFTEEN_SECOND );
//            case(30000):
//                return( IntervalSize.THIRTY_SECOND );
            case(60000):
                return( IntervalSize.ONE_MINUTE );
            case(300000):
                return( IntervalSize.FIVE_MINUTE );
            case(900000):
                return( IntervalSize.FIFTEEN_MINUTE );
//            case(1800000):
//                return( IntervalSize.THIRTY_MINUTE );
            case(3600000):
                return( IntervalSize.HOUR );
        }
        //DAY/WEEK/WEEK/Month are not valid for fromMillis do to DST etc
        throw new RuntimeException("Invalid from Millis: " + intervalMillis );
    }

    /**
     * To Domain Model
     * @return
     */
    public static Interval toDomainInterval( IntervalSize source ) {
        switch( source ) {
            case ONE_MINUTE:
                return( Interval.ONE_MINUTE );
            case FIVE_MINUTE:
                return( Interval.FIVE_MINUTE );
            case FIFTEEN_MINUTE:
                return( Interval.FIFTEEN_MINUTE );
//            case THIRTY_MINUTE:
//                return( Interval.THIRTY_MINUTE );
            case HOUR:
                return( Interval.HOUR );
            case DAY:
                return( Interval.DAY );
            case WEEK:
                return( Interval.WEEK );
            case MONTH:
                return( Interval.MONTH );
        }
        throw new RuntimeException("Unknown Interval " + source );
    }

    public static IntervalSize fromDomainInterval( Interval source ) {
        switch( source ) {
            case ONE_MINUTE:
                return( IntervalSize.ONE_MINUTE );
            case FIVE_MINUTE:
                return( IntervalSize.FIVE_MINUTE );
            case FIFTEEN_MINUTE:
                return( IntervalSize.FIFTEEN_MINUTE );
//            case THIRTY_MINUTE:
//                return( Interval.THIRTY_MINUTE );
            case HOUR:
                return( IntervalSize.HOUR );
            case DAY:
                return( IntervalSize.DAY );
            case WEEK:
                return( IntervalSize.WEEK );
            case MONTH:
                return( IntervalSize.MONTH );
        }
        throw new RuntimeException("Unknown Interval " + source );
    }

    public Interval toDomainInterval() {
        return( toDomainInterval( this ) );
    }

    /**
     * given a sourceSize (For example 15 minute Interval) how many destSize Intervals make it up (for example 15-One Minute intervals)
     * @param sourceSize
     * @param destSize
     * @return
     */
    public static int getNumDestSamples (IntervalSize sourceSize, IntervalSize destSize) {

        if( sourceSize.compare( IntervalSize.DAY) >= 0 ) {
            throw new RuntimeException("Can't interpolate from interval that may be affected by DST/LeadYear etc.");
        }
        if( sourceSize.compare( destSize ) <= 0 ) {
            throw new RuntimeException("Source Interval Size needs to be > Dest Interval Size " + sourceSize + " " + destSize );
        }
        assert sourceSize.getVal() % destSize.getVal() == 0 : "weird interval conversion " + sourceSize + " " + destSize;
        return( sourceSize.getVal() / destSize.getVal() );
    }

    public static IntervalSize fromByte (byte byteVal) {

        switch( byteVal ) {
//            case(1):
//                return( IntervalSize.ONE_SECOND );
//            case(2):
//                return( IntervalSize.FIVE_SECOND );
//            case(3):
//                return( IntervalSize.FIFTEEN_SECOND );
//            case(4):
//                return( IntervalSize.THIRTY_SECOND );
            case(5):
                return( IntervalSize.ONE_MINUTE );
            case(6):
                return( IntervalSize.FIVE_MINUTE );
            case(7):
                return( IntervalSize.FIFTEEN_MINUTE );
//            case(8):
//                return( IntervalSize.THIRTY_MINUTE );
            case(9):
                return( IntervalSize.HOUR );
            case(10):
                return( IntervalSize.DAY );
            case(11):
                return( IntervalSize.WEEK );
            case(12):
                return( IntervalSize.MONTH );
            case(-1):
                return IntervalSize.UNKNOWN;
        }
        //DAY/WEEK/WEEK/Month are not valid for fromMillis do to DST etc
        throw new RuntimeException("Invalid from Byte: " + byteVal );
    }
}
