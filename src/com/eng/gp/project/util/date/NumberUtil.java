package com.eng.gp.project.util.date;


public class NumberUtil {
  //http://libjson-java.sourcearchive.com/documentation/2.2.3-1/JSONUtils_8java-source.html
    public static final boolean isNumber( Object obj ) {
        if( (obj != null && obj.getClass() == Byte.TYPE)
              || (obj != null && obj.getClass() == Short.TYPE)
              || (obj != null && obj.getClass() == Integer.TYPE)
              || (obj != null && obj.getClass() == Long.TYPE)
              || (obj != null && obj.getClass() == Float.TYPE)
              || (obj != null && obj.getClass() == Double.TYPE) ){
           return true;
        }

        return obj instanceof Number;
     }
  //http://libjson-java.sourcearchive.com/documentation/2.2.3-1/JSONUtils_8java-source.html
  public static boolean isNumber( Class clazz ) {
        return clazz != null
              && (Byte.TYPE.isAssignableFrom( clazz ) || Short.TYPE.isAssignableFrom( clazz )
                    || Integer.TYPE.isAssignableFrom( clazz ) || Long.TYPE.isAssignableFrom( clazz )
                    || Float.TYPE.isAssignableFrom( clazz ) || Double.TYPE.isAssignableFrom( clazz ) || Number.class.isAssignableFrom( clazz ));
     }
}
