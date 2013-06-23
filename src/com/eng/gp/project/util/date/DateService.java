package com.eng.gp.project.util.date;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Small helper class for returning a now date setup to
 * start at any arbitrary time in the past and move forward
 * with configurable speed. Useful for simulations.
 */
@ManagedResource(objectName = "com.gridpoint.energy.util:type=DateService")
public class DateService {

    private long realStart;

    private long start = 0;
    private int ratio = 0;
    private boolean simulate = false;

    @ManagedOperation(description = "restart the date service")
    public void restart() {
        setStart( getStart() );
    }

    @ManagedAttribute(description = "get start date")
    public Date getStart() {
        return new Date( start );
    }

    public void setStart( Date start ) {
        realStart = realNow();
        this.start = start.getTime();
    }

    @ManagedOperation(description = "set start date (MM-dd-yyyy)")
    public void setStartString( String start ) {
        DateFormat dateFormat = new SimpleDateFormat( "MM-dd-yyyy" );
        try {
            setStart( dateFormat.parse( start ) );
        } catch ( ParseException pe ) {
            throw new RuntimeException( pe );
        }
    }

    public void setRatio( int ratio ) {
        if ( ratio <= 1 ) {
            throw new RuntimeException( "must set a ratio greater than 1.0" );
        }
        this.ratio = ratio;
    }

    public void setSimulate( boolean simulate ) {
        this.simulate = simulate;
    }

    public Date getNowDate() {
        if ( simulate ) {
            return new Date( getNow() );
        } else {
            return new Date();
        }
    }

    public long getNow() {
        if ( simulate ) {
            long realDelta = realNow() - realStart;

            // Update now to be a ratio of the real delta
            // between begin and now.  So if delta is 100ms,
            // and ratio is 10, then sim now would be start time
            // plus 1000ms (1s)
            return start + (realDelta * ratio);
        } else {
            return realNow();
        }
    }

    private long realNow() {
        return (new Date()).getTime();
    }
}
