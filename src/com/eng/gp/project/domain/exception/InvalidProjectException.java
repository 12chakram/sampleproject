package com.eng.gp.project.domain.exception;

public class InvalidProjectException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = 1372756677872160178L;

    private long projectId;
    
    public InvalidProjectException ()
    {
        super();
    }

    public InvalidProjectException(String message) {
        super(message);
    }
    
    public InvalidProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProjectException(Throwable cause) {
        super(cause);
    }
    
    public InvalidProjectException(final long projectId)
    {
        super("Couldn't find project ID #"+projectId);
        this.projectId = projectId;
    }

	public long getProjectId() {
		return projectId;
	}

    
   }
