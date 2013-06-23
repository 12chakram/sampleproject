package com.eng.gp.project.domain.exception;

public class InvalidProjectTypeException extends Exception {

    private static final long serialVersionUID = 0L;

    private long projectTypeId;

    public InvalidProjectTypeException ()
    {
        super();
    }

    public InvalidProjectTypeException (Throwable t)
    {
        super(t);
    }

    public InvalidProjectTypeException(String message) {
        super(message);
    }

    public InvalidProjectTypeException(final long projectTypeId)
    {
        super("Couldn't find project type ID #"+projectTypeId);
        this.projectTypeId = projectTypeId;
    }

    public long getProjectTypeId()
    {
        return projectTypeId;
    }

}
