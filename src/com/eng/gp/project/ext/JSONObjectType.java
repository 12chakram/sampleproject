package com.gridpoint.energy.datamodel.ext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Teaches Hibernate how to persist and load {@link JSONObject}s.
 *
 * Some inspiration from http://blog.janjonas.net/2010-04-27/hibernate-json-usertype-persist-json-objects
 *
 * Warning: JSONObjects are not serializable, and therefore probably not suitable for remoted JPA Entities.
 */
public class JSONObjectType implements UserType
{
    private final static int[] SQL_TYPES = { Types.LONGVARCHAR };

    public int[] sqlTypes()
    {
        return SQL_TYPES;
    }

    public Class returnedClass()
    {
        return JSONObject.class;
    }

    public boolean equals(Object o1, Object o2) throws HibernateException
    {
        return EqualsBuilder.reflectionEquals(o1, o2);
    }

    public int hashCode(Object o) throws HibernateException
    {
        return String.valueOf(o).hashCode();
    }

    public Object nullSafeGet(ResultSet resultSet, String[] fieldNames, Object o) throws HibernateException, SQLException
    {
        if (resultSet.wasNull())
        {
            return null;
        }
        else if (fieldNames.length > 1)
        {
            throw new IllegalArgumentException("More than one fieldname: "+fieldNames.length);
        }
        else
        {
            try
            {
                final String string = resultSet.getString(fieldNames[0]);

                if (string==null)
                    return null;
                else
                    return new JSONObject(string);
            }
            catch (JSONException e)
            {
                throw new IllegalStateException(e);
            }
        }
    }

    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i) throws HibernateException, SQLException
    {
        if (value == null)
        {
            preparedStatement.setNull(i, SQL_TYPES[0]);
        }
        else
        {
            preparedStatement.setString(i, String.valueOf(value));
        }
    }

    public Object deepCopy(Object value) throws HibernateException
    {
        if (value == null) return value;

        try
        {
            return new JSONObject(String.valueOf(value));
        }
        catch (JSONException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public boolean isMutable()
    {
        return true;
    }

    public Serializable disassemble(Object value) throws HibernateException
    {
        return String.valueOf(value);
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException
    {
        return deepCopy(cached);
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException
    {
        return deepCopy(original);
    }
}
