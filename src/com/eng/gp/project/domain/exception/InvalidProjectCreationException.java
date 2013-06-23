package com.eng.gp.project.domain.exception;

public class InvalidProjectCreationException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = 1372756677872160178L;

    public InvalidProjectCreationException ()
    {
        super();
    }

    public InvalidProjectCreationException(String message) {
        super(message);
    }
    
    public InvalidProjectCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProjectCreationException(Throwable cause) {
        super(cause);
    }

}
