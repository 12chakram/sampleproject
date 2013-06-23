package com.eng.gp.project.ext;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Supports storing UTC times into SQL timestamp fields via Hibernate.
 * (Hibernate's default * behavior is to store based on the timezone of the
 * server.)
 */
public class UtcTimestampType implements UserType {

    // the SQL type this type manages
    private static int [] SQL_TYPES = { Types.TIMESTAMP };

    // the Calendar to hold the UTC timezone
    private static Calendar utcCalendar = Calendar.getInstance();
    static {
        // set the timezone for the calendar to UTC (use the GMT timezone)
        utcCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable,
     * java.lang.Object)
     */
    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return deepCopy(cached);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
     */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return ( value == null ) ? null : new Timestamp(( (Date) value )
            .getTime());
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
     */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#equals(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ( x == null ) ? ( y == null ) : x.equals(y);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
     */
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#isMutable()
     */
    @Override
    public boolean isMutable() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet,
     * java.lang.String[], java.lang.Object)
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String [] names, Object owner)
            throws HibernateException, SQLException {

    	return rs.getTimestamp(names[0], utcCalendar);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
     * java.lang.Object, int)
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        // note we use deep copy to upconvert from Date to Timestamp
        st.setTimestamp(index, (Timestamp) deepCopy(value), utcCalendar);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#replace(java.lang.Object,
     * java.lang.Object, java.lang.Object)
     */
    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return deepCopy(original);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#returnedClass()
     */
    @Override
    public Class returnedClass() {
        return Date.class;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#sqlTypes()
     */
    @Override
    public int [] sqlTypes() {
        return SQL_TYPES;
    }
}
