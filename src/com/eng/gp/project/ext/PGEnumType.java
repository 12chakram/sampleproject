package com.gridpoint.energy.datamodel.ext;

import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * Implements support for PostgreSQL enum types as properties Hibernate entities.
 *
 * Borrowed substantially from
 *
 * http://anismiles.wordpress.com/2010/08/04/postgres-enum-with-hibernate/
 * http://stackoverflow.com/questions/735732/mapping-enum-to-a-table-with-hibernate-annotation
 *
 * Supported Hibernate entry parameters:
 *    enumClassName (required)
 *    preserveCharacterCasing (optional) defaults to false.
 */
public class PGEnumType implements UserType, ParameterizedType
{
    // Enum  class under observation
    private Class<Enum> enumClass;

    private boolean preserveCharacterCasing;

    @SuppressWarnings("unchecked")
    public void setParameterValues(Properties parameters)
    {
        final String enumClassName = (String) parameters.get("enumClassName");
        if (enumClassName==null)
            throw new IllegalArgumentException("Annotation must provide enumClassName Hibernate entry parameter");

        try
        {
            enumClass = (Class<Enum>) Class.forName(enumClassName);

            if (parameters.containsKey("preserveCharacterCasing"))
                preserveCharacterCasing = Boolean.valueOf(parameters.getProperty("preserveCharacterCasing"));
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException
    {
        return cached;
    }

    public Object deepCopy(Object value) throws HibernateException
    {
        return value;
    }

    public Serializable disassemble(Object value) throws HibernateException
    {
        return (Enum) value;
    }

    public boolean equals(Object x, Object y) throws HibernateException
    {
        return x == y;
    }

    public int hashCode(Object x) throws HibernateException
    {
        return x.hashCode();
    }

    public boolean isMutable()
    {
        return true;
    }

    @SuppressWarnings("unchecked")
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
            throws HibernateException, SQLException
    {
        Object object = rs.getObject(names[0]);
        if (rs.wasNull()) {
            return null;
        }

        // Notice how Object is mapped to PGobject. This makes this implementation Postgres specific
        if (object instanceof PGobject) {
            PGobject pg = (PGobject) object;

            if (preserveCharacterCasing)
                return Enum.valueOf(enumClass, pg.getValue());
            else
                return pg.getValue()!=null?Enum.valueOf(enumClass, pg.getValue().toUpperCase()):null;
        }
        return null;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index)
            throws HibernateException, SQLException
    {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
            // UPDATE: To support NULL insertion, change to: st.setNull(index, 1111);
        } else {
            // Notice 1111 which java.sql.Type for Postgres Enum

            final String enumValueName = String.valueOf(value);

            if (preserveCharacterCasing)
                st.setObject(index, enumValueName, 1111);
            else
                st.setObject(index, enumValueName.toLowerCase(), 1111);
        }
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException
    {
        return original;
    }

    public Class returnedClass()
    {
        return enumClass;
    }

    public int[] sqlTypes()
    {
        return new int[]{Types.VARCHAR};
        // UPDATE: To support NULL insertion, change to: return new int[] { 1111 };
    }
}

