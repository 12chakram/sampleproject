package com.eng.gp.project.ext;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;

/**
 * An optimized version of Double[] that uses primitive doubles to store data, and a bitSet to denote null or not.
 * Why? because a Double is 3x bigger than a double, and if we are storing/moving/using a lot, then it makes sense..
 * otherwise its an over complication and you shouldn't use this.
 * Not synchronized.
 * Serializable is just for unit testing with HSQLDB...
 */
public class PrimitiveDoubleArray implements Cloneable, Serializable {
    private static final long serialVersionUID = 123L;


    private final BitSet    isValidBitSet;
    private       double[]  values          = null;


    public PrimitiveDoubleArray( int size ) {
        isValidBitSet = new BitSet(size);
        values        = new double[size];
    }

    public PrimitiveDoubleArray( double[] values, BitSet isValidBitSet ) {
        this.values        = values;
        this.isValidBitSet = isValidBitSet;
    }

    public PrimitiveDoubleArray( double[] values ) {
        this.values        = values;
        this.isValidBitSet = new BitSet();
        this.isValidBitSet.set(0, values.length,true );
    }

    public boolean isNull( int index ) {
        return( !isValidBitSet.get( index ) );
    }

    public double get( int index ) {
        if( isValidBitSet.get( index ) ) {
            return(values[index]);
        }else {
            throw new NullPointerException();
        }
    }
    public Double getDouble( int index ) {
        if( isValidBitSet.get( index ) ) {
            return(values[index]);
        }else {
            return( null );
        }
    }

    public int size() {
        return( values.length );
    }

    public void setDouble( int index, Double value ) {
        if( value == null ) {
            setNull( index );
        }else {
            isValidBitSet.set(index);
            values[index] = value;
        }
    }

    public void set( int index, double value ) {
        isValidBitSet.set( index );
        values[index] = value;
    }

    public void setNull( int index ) {
        isValidBitSet.clear( index );
        values[index] = 0;
    }

    @SuppressWarnings ({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone () throws CloneNotSupportedException {
        return( new PrimitiveDoubleArray(Arrays.copyOf( this.values, this.values.length ), (BitSet)this.isValidBitSet.clone() ) );
    }

    @SuppressWarnings ({"RedundantIfStatement"})
    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrimitiveDoubleArray that = (PrimitiveDoubleArray) o;

        if (!Arrays.equals(values, that.values) &&  isValidBitSet.equals( that.isValidBitSet )) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode () {
        return Arrays.hashCode(values);
    }

    public Double[] toDoubleArray() {
        Double[] result = new Double[this.values.length];
        for( int x=0;x<size();x++) {
            result[x] = getDouble(x);
        }
        return( result );
    }

    @Override
    public String toString () {
        return "PrimitiveDoubleArray{" + "length=" + values.length + '}';
    }

    public int getHighestSetIndex () {
        return( isValidBitSet.length() - 1 );
    }

    /**
     * shrink this collection so that it only contains up to and including lastValidIndex
     * @param lastValidIndex
     */
    public void trimTo (final int lastValidIndex) {
        if( lastValidIndex < isValidBitSet.length() ) {
            isValidBitSet.clear( lastValidIndex+1, isValidBitSet.length()+1 );
        }
        double[] newVals = new double[lastValidIndex+1];
        System.arraycopy( this.values, 0, newVals, 0, newVals.length );
        this.values = newVals;
    }
}
