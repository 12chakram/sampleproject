package com.eng.gp.project.util.sql;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

/**
 *
 */
public class PostgresArray implements Array {


    private final String sqlString;
    private final String typeName;
    private final int    typeNum;


    private PostgresArray(String sqlString, String typeName, int typeNum) throws SQLException {
        this.sqlString = sqlString;
        this.typeName  = typeName;
        this.typeNum   = typeNum;
    }

    public static Array createArray(int[] array) throws SQLException {
        if (array == null) {
            return new PostgresArray(null, "int4", Types.INTEGER );
        }

        StringBuilder sb = new StringBuilder("{");
        for ( int x = 0; x<array.length; x++) {
            if (x > 0) {
                sb.append(',');
            }
            sb.append('\"').append(array[x]).append('\"');
        }
        sb.append('}');

        return new PostgresArray(sb.toString(), "int4", Types.INTEGER );
    }
    public static Array createArray(Integer[] array) throws SQLException {
        if (array == null) {
            return new PostgresArray(null, "int4", Types.INTEGER );
        }

        StringBuilder sb = new StringBuilder("{");
        for ( int x = 0; x<array.length; x++) {
            if (x > 0) {
                sb.append(',');
            }
            if( array[x] == null ) {
                sb.append("NULL");
            }else {
                sb.append('\"').append(array[x]).append('\"');
            }
        }
        sb.append('}');

        return new PostgresArray(sb.toString(), "int4", Types.INTEGER );
    }

    public static Array createArray(long[] array) throws SQLException {
        if (array == null) {
            return new PostgresArray(null, "int8", Types.BIGINT );
        }

        StringBuilder sb = new StringBuilder("{");
        for ( int x = 0; x<array.length; x++) {
            if (x > 0) {
                sb.append(',');
            }
            sb.append('\"').append(array[x]).append('\"');
        }
        sb.append('}');

        return new PostgresArray(sb.toString(), "int8", Types.BIGINT );
    }

    @SuppressWarnings ({"UnusedDeclaration"})
    public static Array createArray(double[] array) throws SQLException {
        if (array == null) {
            return new PostgresArray(null, "float8", Types.DOUBLE );
        }

        StringBuilder sb = new StringBuilder("{");
        for ( int x = 0; x<array.length; x++) {
            if (x > 0) {
                sb.append(',');
            }
            sb.append('\"').append(array[x]).append('\"');
        }
        sb.append('}');

        return new PostgresArray(sb.toString(), "float8", Types.BIGINT );
    }
    public static Array createArray(Double[] array) throws SQLException {
        if (array == null) {
            return new PostgresArray(null, "float8", Types.DOUBLE );
        }

        StringBuilder sb = new StringBuilder("{");
        for ( int x = 0; x<array.length; x++) {
            if (x > 0) {
                sb.append(',');
            }
            if( array[x] == null ) {
                sb.append("NULL");
            }else {
                sb.append('\"').append(array[x]).append('\"');
            }
        }
        sb.append('}');

        return new PostgresArray(sb.toString(), "float8", Types.BIGINT );
    }

    @Override
    public String getBaseTypeName ()
            throws SQLException {
        return( this.typeName );
    }

    @Override
    public int getBaseType ()
            throws SQLException {
        return( this.typeNum );
    }

    @Override
    public Object getArray ()
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public Object getArray (Map<String, Class<?>> stringClassMap)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public Object getArray (long l, int i)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public Object getArray (long l, int i, Map<String, Class<?>> stringClassMap)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public ResultSet getResultSet ()
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public ResultSet getResultSet (Map<String, Class<?>> stringClassMap)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public ResultSet getResultSet (long l, int i)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public ResultSet getResultSet (long l, int i, Map<String, Class<?>> stringClassMap)
            throws SQLException {
        throw new SQLException("Not supported");
    }

    @Override
    public void free ()
            throws SQLException {
    }

    @Override
    public String toString() {
        return( this.sqlString );
    }

}
