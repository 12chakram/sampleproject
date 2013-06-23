package com.eng.gp.project.util.date;

public class SkippedHourException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Override
    public synchronized Throwable fillInStackTrace() {
        /*
         * To save time, and because this exception is only thrown when expected, do not fill in a stack trace
         */
        return this;
    }
}
