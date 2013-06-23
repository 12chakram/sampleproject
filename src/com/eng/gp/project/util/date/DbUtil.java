package com.eng.gp.project.util.date;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.persistence.Query;

public class DbUtil {
    public static <T> String createParameterList(int startNumber, Collection<T> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?" + (startNumber + i));
        }

        return sb.toString();
    }

    public static <T> int setParameterList(Query query, int firstParamIndex, Collection<T> params) {
        int currentParamIndex = firstParamIndex;
        for (T param : params) {
            query.setParameter(currentParamIndex, param);
            currentParamIndex++;
        }

        return currentParamIndex;
    }

    
    /**
     * Returns a string formatted to follow the IN keyword in a SQL statement ie. "(1,2,3)".
     * 
     * @param ids The collection of ids to be joined together for the in clause.
     * @return a string formatted to follow the SQL keyword IN
     */
    public static String inBuilder( Collection<Long> ids ) {
        if( ids == null  ||  ids.size() == 0 ) {
            return("()");
        }
        StringBuilder queryStr = new StringBuilder("(");
        for (Long chanId : ids) {
            queryStr.append(chanId).append(",");
        }
        queryStr.deleteCharAt(queryStr.length() - 1);
        queryStr.append(")");
        return( queryStr.toString() );
    }

    public static void close(Statement stmt) {
        if (stmt == null) return;
        try {
            stmt.close();
        } catch (SQLException e) {
            // Error closing, not much to do
        }
    }

    public static void close(Connection conn) {
        if (conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            // Error closing, not much to do
        }
    }
}
