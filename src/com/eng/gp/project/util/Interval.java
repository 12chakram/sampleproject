package com.eng.gp.project.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Range from start to end. (longs)
 */
public class Interval
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final long start;
    private final long end;

    /**
     * simple arithmetic to split the range.
     * @param firstEndpointId
     * @param lastEndpointId
     * @param divisions
     * @param lastIsOpenEnd If true, then split up the intervals as usual, but the last Interval will be open ended (Long.MAX)
     * @return
     */
    public static Interval[] makeIntervals( final long      firstEndpointId,
                                            final long      lastEndpointId,
                                            final int       divisions,
                                            final boolean   lastIsOpenEnd )
    {
        List<Interval> intervals = new ArrayList<Interval>();

        if( lastEndpointId < firstEndpointId )
        {
            throw new RuntimeException("makeRanges out of order " + firstEndpointId + " " + lastEndpointId );
        }

        long numValues = lastEndpointId - firstEndpointId;
        long blockSize = (long) Math.ceil( ((double)numValues) / divisions );

        long startEndpoint = firstEndpointId;

        while( startEndpoint <= lastEndpointId )
        {
            long nextEnd = Math.min(startEndpoint + blockSize, lastEndpointId);
            intervals.add( new Interval( startEndpoint, Math.min(startEndpoint + blockSize, lastEndpointId) ) );
            startEndpoint = nextEnd + 1;
        }
        if( lastIsOpenEnd ) {
            Interval lastInterval = intervals.get( intervals.size() - 1 );
            intervals.set( intervals.size() -1, new Interval( lastInterval.getStart(), Long.MAX_VALUE) );
        }

        return( intervals.toArray( new Interval[ intervals.size()] ) );
    }


    public Interval (long start, long end)
    {
        this.start = start;
        this.end = end;
        if( start > end )
        {
            throw new RuntimeException("Start > End" + start + " " + end );
        }
    }

    public long getStart ()
    {
        return start;
    }

    public long getEnd ()
    {
        return end;
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Interval longRange = (Interval) o;

        if (end != longRange.end)
        {
            return false;
        }
        if (start != longRange.start)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode ()
    {
        int result = (int) (start ^ (start >>> 32));
        result = 31 * result + (int) (end ^ (end >>> 32));
        return result;
    }

    /**
     * Returns true if the ranges overlap
     * @param range
     * @return
     */
    public boolean overlaps( Interval range )
    {
        if( range == null )
        {
            return( false );
        }
        return( this.start <= range.end  &&  this.end >= range.start );
    }


    public static int compareByStart (Interval first, Interval other)
    {
        if( other == null )
        {
            return( 1 );
        }
        if( first == null )
        {
            return( -1 );
        }
        if( first.start > other.start )
        {
            return( 1 );
        }
        else if( first.start < other.start )
        {
            return( -1 );
        }
        else
        {
            return( 0 );
        }
    }

    public boolean contains (long point)
    {
        return( start <= point  &&  end >= point );
    }

    @Override
    public String toString ()
    {
        return "Interval{" + "start=" + start + ", end=" + end + '}';
    }
}
