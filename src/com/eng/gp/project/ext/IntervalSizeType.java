package com.eng.gp.project.ext;

import com.eng.gp.project.domain.IntervalSize;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

/**
 *
 */
public class IntervalSizeType implements UserType
{
    // the SQL type this type manages
    private static final int [] SQL_TYPES = { Types.SMALLINT };

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable,
     * java.lang.Object)
     */
    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
     */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return( value );
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
     */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
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
        return( x.hashCode() );
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#isMutable()
     */
    @Override
    public boolean isMutable() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet,
     * java.lang.String[], java.lang.Object)
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String [] names, Object owner) throws HibernateException, SQLException {
        short value = rs.getShort(names[0]);
        return(  IntervalSize.fromByte((byte) value) );
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
        st.setShort(index, ((IntervalSize) value).getByteId() );
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#replace(java.lang.Object,
     * java.lang.Object, java.lang.Object)
     */
    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return(original);
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#returnedClass()
     */
    @Override
    public Class returnedClass() {
        return IntervalSize.class;
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
