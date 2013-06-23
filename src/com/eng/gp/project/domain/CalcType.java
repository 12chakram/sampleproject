package com.eng.gp.project.domain;

/**
 * Enumeration that describes the type of calculation that is done on received channel values.
 * Is the value of an interval the  average, Min() Max() or Last() of all samples inside that interval?
 */
public enum CalcType {

    /**
     * The Value is the average... However.. when down sample (going from a 15 minute sample to a 1 minute sample)
     * The 1 minute sample is just the repeated value, NOT interpolated.
     */
    AVG_CALC_TYPE((byte)1,"average"),

    /**
     * The Value si the min value received
     */
    MIN_CALC_TYPE((byte)2,"min"),

    /**
     * The Value is the Max value received
     */
    MAX_CALC_TYPE((byte)3,"max"),

    /**
     * The value is the Last value received
     */
    LAST_CALC_TYPE((byte)4,"last"),

    /**
     * Sum Calc Type interpolates so that subIntervals add up to the source interval.
     * For example, given a 15 minute interval with Val X, then the component 1 minute intervals would each be X/15
     */
    SUM_CALC_TYPE((byte)5,"sum");

    private final byte   val;
    private final String dbString;

    private CalcType( byte val, String dbString ) {
        this.val=val;
        this.dbString = dbString;
    }

    public byte getVal () {
        return val;
    }

    public static CalcType fromByte (byte byteVal) {
        switch( byteVal ) {
            case 1:
                return( AVG_CALC_TYPE );
            case 2:
                return( MIN_CALC_TYPE );
            case 3:
                return( MAX_CALC_TYPE );
            case 4:
                return( LAST_CALC_TYPE );
            case 5:
                return( SUM_CALC_TYPE );
        }
        throw new RuntimeException("InvalidCalcType: " + byteVal );
    }

    public String toDbString() {
        return( this.dbString );
    }
    public static CalcType fromDbString(String str ) {
        if( "average".equals(str) ) {
            return( AVG_CALC_TYPE );
        }
        else if( "min".equals(str) ) {
            return( MIN_CALC_TYPE );
        }
        else if( "max".equals(str) ) {
            return( MAX_CALC_TYPE );
        }
        else if( "last".equals(str) ) {
            return( LAST_CALC_TYPE );
        }
        else if( "sum".equals(str) ) {
            return( SUM_CALC_TYPE );
        } else {
            throw new RuntimeException("No such CalcType:" + str );
        }
    }

}
