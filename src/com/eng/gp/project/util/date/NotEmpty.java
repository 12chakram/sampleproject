package com.eng.gp.project.util.date;

import java.util.Collection;

public final class NotEmpty {

    public static void verify(String o, String paramName) {
        if(o == null || "".equals(o)) {
            throw new IllegalArgumentException(paramName + " is invalid");
        }
    }

    public static void verify(Collection<?> o, String paramName) {
        if(o == null || o.isEmpty()) {
            throw new IllegalArgumentException(paramName + " is invalid");
        }
    }
    
    public static void failIf(boolean condition, String message) {
        if(condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
