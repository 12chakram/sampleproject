package com.eng.gp.project.util.sql;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Because Postgres doesn't implement setQueryTimeout, we implement our own.
 * If the Query hasn't finished in the time specified on setQueryTimeout (+ 1 second) then we will
 * Cancel it outselves... We give it the extra second in case some day the querytimeout is implemented...
 */
public class JdbcExecutor
{
    private static final int            EXECUTES_BEFORE_PURGE   = 100;
	private static final Logger         logger                  = Logger.getLogger( JdbcExecutor.class );
	private static final Timer          timer                   = new Timer("JdbcExecutor",true);
    private static final AtomicInteger  executeCount            = new AtomicInteger();

	private final PreparedStatement statement;
	private final TimerTask         task;
    private final long              timeoutLengthSeconds;
	private       boolean			timedOut                = false;
	private       boolean			gotResult               = false;


    public JdbcExecutor(PreparedStatement statement, long timeoutLengthSeconds) {
		this.statement              = statement;
        this.timeoutLengthSeconds   = timeoutLengthSeconds;
		this.task                   = new TimerTask() {
			@Override
			public void run() {
				try{
					handleTimeout();
				}catch(Throwable t) {
					logger.error("jdbc execute failed",t);
				}
			}
		};
	}

	public boolean execute()
		throws SQLException {
        boolean result;

        preSchedule();

        try{
            result = statement.execute();
            if( logger.isTraceEnabled() ) {
                logger.trace("Got Result:" );
            }
        }finally {
            try{
                this.task.cancel();
            }catch(Throwable ignored){}
        }

        postExecute();

        return( result );
	}


	public ResultSet executeQuery()
		throws SQLException {

        ResultSet result = null;

        preSchedule();

        try {
		    result = statement.executeQuery();
        }finally {
            try{
                this.task.cancel();
            }catch(Throwable ignored){}
        }
		if( logger.isTraceEnabled() ) {
			logger.trace("Got Result:" );
		}

        postExecute();

        return( result );
	}

    synchronized private void preSchedule() {

        if( timedOut || gotResult ) {
            throw new RuntimeException("Don't Re-Use this object");
        }

        try {
            if( logger.isDebugEnabled() ) {
                logger.debug("starting Execute timer:" + ((statement.getQueryTimeout() + 1) * 1000) );
            }
        }catch( Exception ex ) {
            logger.error("execute",ex);
        }
        timer.schedule( this.task, (timeoutLengthSeconds + 1 ) * 1000 );
    }

    synchronized private void postExecute() throws SQLException {
        if( executeCount.addAndGet(1) % EXECUTES_BEFORE_PURGE == 0 ) {
            //had to do this because task.cancel() does not clean up. and I got OOM.
            timer.purge();
        }

        if( timedOut ) {
            throw new SQLException("TimedOut");
        }
        else {
            this.gotResult = true;
        }
    }

	synchronized private void handleTimeout()
	{
		if( logger.isDebugEnabled() ) {
			logger.debug("handle Timeout! " + this.gotResult);
		}
		if( !this.gotResult ) {
			try {
				timedOut = true;
				logger.warn("handle Timeout!");
			    statement.cancel();
			}catch( Exception ex ) {
				logger.warn("handleTimeout cancel failed", ex );
			}
		}
	}
}
