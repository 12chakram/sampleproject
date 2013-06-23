package com.eng.gp.project.domain.exception;


/**
 * 
 * This exception is thrown when a property is not accepted by the throwing method.
 * 
 * Properties are generic constructs that serve different purposes in different contexts.
 * Passing a user profile property to a device property processor is an example of a 
 * condition that may generate this exception.
 *
 */
public class InvalidPropertyException extends Exception {

    private static final long serialVersionUID = 0L;

    public InvalidPropertyException () {
        super();
    }

    public InvalidPropertyException (String message) {
        super(message);
    }

    public InvalidPropertyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidPropertyException(Throwable throwable) {
        super(throwable);
    }
}