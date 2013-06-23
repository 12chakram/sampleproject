package com.eng.gp.project.util.date;


public final class NotNull {

    /**
     * Determines if a parameter is null.
     * @param o the parameter to be tested
     * @param paramName the name of the parameter
     * @throws IllegalArgumentException if {@code O} is null.
     */
    public static void verify(Object o, String paramName) {
        if(o == null) {
            throw new IllegalArgumentException(paramName + " is null");
        }
    }
}
