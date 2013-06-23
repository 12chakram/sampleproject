package com.gridpoint.energy.datamodel.ext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.xml.sax.SAXException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Teaches Hibernate how to persist and load {@link SerializableXmlDocument}s.
 *
 * (Since C3P0+Spring won't permit access to SQLXML, we target TEXT types here rather than XML.)
 *
 * @author dhorlick
 */
public class SerializableXmlDocumentType implements UserType
{
    private final static int[] SQL_TYPES = { Types.LONGVARCHAR };

    public int[] sqlTypes()
    {
        return SQL_TYPES;
    }

    public Class returnedClass()
    {
        return SerializableXmlDocument.class;
    }

    public boolean equals(final Object x, final Object y) throws HibernateException
    {
        final boolean result = EqualsBuilder.reflectionEquals(x, y);
        return result;
    }

    public int hashCode(final Object x) throws HibernateException
    {
        return HashCodeBuilder.reflectionHashCode(x);
    }

    public Object nullSafeGet(final ResultSet resultSet, final String[] fieldNames, final Object owner) throws HibernateException, SQLException
    {

        if (fieldNames.length > 1)
        {
            throw new IllegalArgumentException("More than one fieldname: "+fieldNames.length);
        }
        else
        {
            /*
            final Document doc = (Document) resultSet.getSQLXML(fieldNames[0]).getSource(DOMSource.class).getNode();
                ^ Sadly, C3P0+Spring won't tolerate this.
            */
        	
			if(resultSet == null || resultSet.getString(fieldNames[0]) == null || resultSet.getString(fieldNames[0]).equals("NULL") ){
				
				return null;
			}
            final String stringifiedXml = resultSet.getString(fieldNames[0]);
            if (resultSet.wasNull() || stringifiedXml==null)
                return null;

            try
            {
                return SerializableXmlDocument.build(stringifiedXml);
            }
            catch (SAXException e)
            {
                throw new IllegalStateException(e); // upstream bug or disgruntled DBA
            }
        }
    }

    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index) throws HibernateException, SQLException
    {
        if (value == null)
        {
            preparedStatement.setNull(index, SQL_TYPES[0]);
        }
        else
        {
            final SerializableXmlDocument serializableXmlDocument =  (SerializableXmlDocument) value;

            /*
            final Connection conn = preparedStatement.getConnection().unwrap(Jdbc4Connection.class);
            final SQLXML sqlXML = conn.createSQLXML();
            sqlXML.setResult(DOMResult.class).setNode(serializableXmlDocument.getDocument());
            preparedStatement.setSQLXML(index, sqlXML);

                ^ Sadly, C3P0+Spring won't tolerate this.
            */

            preparedStatement.setString(index, serializableXmlDocument.stringify());
        }
    }

    public Object deepCopy(final Object value) throws HibernateException
    {
        if (value == null) return value;

        final SerializableXmlDocument serializableXmlDocument = (SerializableXmlDocument) value;

        return serializableXmlDocument.clone();
    }

    public boolean isMutable()
    {
        return true;
    }

    public Serializable disassemble(final Object value) throws HibernateException
    {
        return (Serializable) deepCopy(value);
    }

    public Object assemble(final Serializable cached, final Object owner) throws HibernateException
    {
        return deepCopy(cached);
    }

    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException
    {
        return deepCopy(original);
    }
}
