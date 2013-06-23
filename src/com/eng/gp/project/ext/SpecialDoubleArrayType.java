package com.eng.gp.project.ext;

import com.eng.gp.project.ext.PrimitiveDoubleArray;
import com.eng.gp.project.util.sql.PostgresArray;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.*;
import java.sql.*;


/**
 *
 */
public class SpecialDoubleArrayType
        implements UserType
{
    //In order to be compatible with HSQLDB, we are serializing as blob.. otherwise use SqlArray Type
    private static boolean isTestMode = false;

    // the SQL type this type manages
    private static final int [] SQL_TYPES = { Types.ARRAY };

    // The SQL Type if we are in Test mode (in HSQLDB) that doens't support array
    private static final int [] TEST_SQL_TYPES = { Types.VARBINARY };

    /**
     * Set to true ONLY if running in HSQLDB.
     * @param isTestMode
     */
    public static void setIsTestMode (boolean isTestMode) {
        SpecialDoubleArrayType.isTestMode = isTestMode;
    }

    public static boolean isTestMode(){
        return SpecialDoubleArrayType.isTestMode;
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
        PrimitiveDoubleArray source = (PrimitiveDoubleArray) value;
        try {
            return ( source == null ) ? null : source.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); //this never happens.. I implement it.
        }
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
        return( x.hashCode() );
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
    public Object nullSafeGet(ResultSet rs, String [] names, Object owner) throws HibernateException, SQLException {

        if( isTestMode ) {
            ByteArrayInputStream inBytes  = null;
            ObjectInputStream    inStream = null;
            try {
                inBytes  = new ByteArrayInputStream( rs.getBytes(names[0]));
                inStream = new ObjectInputStream( inBytes );
                return( inStream.readObject() );
            }catch(Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if( inBytes != null ) {
                        inBytes.close();
                    }
                }
                catch (Exception ignored) {}
                try {
                    if( inStream != null ) {
                        inStream.close();
                    }
                }
                catch (Exception ignored) { }
            }

        }else {
            Array sqlArray = rs.getArray(names[0]);
            if( sqlArray == null ) {
                return( null );
            }
            Double[] dblArray = (Double[])sqlArray.getArray();
            if( dblArray == null ) {
                return( null );
            }
            PrimitiveDoubleArray result = new PrimitiveDoubleArray( dblArray.length );
            for( int x=0;x<dblArray.length;x++) {
                result.setDouble(x, dblArray[x] );
            }
            return( result );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
     * java.lang.Object, int)
     */
    @SuppressWarnings ({"RedundantCast"})
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException {
        if( isTestMode ) {
            try {
                ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
                ObjectOutputStream    os       = new ObjectOutputStream( outBytes );
                os.writeObject( value );
                os.close();
                outBytes.close();
                st.setBytes(index, outBytes.toByteArray() );
            }catch(Exception e ) {
                throw new RuntimeException(e);
            }
        }else {
            st.setArray( index, PostgresArray.createArray( value==null?(Double[])null:((PrimitiveDoubleArray) value).toDoubleArray()) );
        }
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
    @SuppressWarnings("unchecked")
    public Class returnedClass() {
        return PrimitiveDoubleArray[].class;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.usertype.UserType#sqlTypes()
     */
    @Override
    public int [] sqlTypes() {
        if( isTestMode ) {
            return TEST_SQL_TYPES;
        }else {
            return SQL_TYPES;
        }
    }

}
