package com.eng.gp.project.domain.exception;


/**
 *
 * This exception is thrown when a premises object cannot be resolved based on identifying
 * criteria provided to the throwing method.
 *
 */
public class InvalidPremisesException extends Exception {

    private static final long serialVersionUID = 0L;

    private long premisesId;

    public InvalidPremisesException ()
    {
        super();
    }

    public InvalidPremisesException (Throwable t)
    {
        super(t);
    }

    public InvalidPremisesException(String message) {
        super(message);
    }

    public InvalidPremisesException(final long premisesId)
    {
        super("Couldn't find premises ID #"+premisesId);
        this.premisesId = premisesId;
    }

    public long getPremisesId()
    {
        return premisesId;
    }
}
