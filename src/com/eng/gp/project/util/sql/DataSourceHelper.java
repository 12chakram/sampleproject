package com.eng.gp.project.util.sql;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 */
public class DataSourceHelper
{
    private static final Logger logger          = Logger.getLogger( DataSourceHelper.class );


    public static Connection getConnection( final DataSource dataSource )
        throws SQLException
    {
        return( getConnection( dataSource, 60000 ) );
    }


    /**
     * Wrapper around dataSource.getConnection to log begin/end/etc
     * I would just extend getConnection() on DataSource, if it wasn't so abstracted/etc
     * @param dataSource
     * @return
     * @throws SQLException
     */
    public static Connection getConnection( final DataSource dataSource, final int checkoutWarn )
        throws SQLException
    {
        Connection connection = null;
        logger.debug("getConnection start " );
        long start = System.currentTimeMillis();
        connection = dataSource.getConnection();
        long duration = System.currentTimeMillis() - start;
        if( duration > checkoutWarn ) {
            logger.warn("Checkout of connection took long time:" + duration + " " + dataSource );
        }
        logger.debug("getConnection end. Length:" + duration );
        return( connection );
    }

}
