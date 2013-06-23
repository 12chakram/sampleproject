package com.eng.gp.project.util.date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Util for looking up spring beans using a static class reference
 * so that the spring context can be accessed by classes that are
 * not instantiated by spring. For example, if an instance of an
 * object is instantiated via de-serialization, that object will
 * not be part of the spring application context. This class allows
 * the instance to retrieve a bean by name.
 */
public class StaticContextLookup {
    private static StaticContextLookup instance;

    private static final Logger logger = Logger.getLogger( StaticContextLookup.class );

    @Autowired
    ApplicationContext context;

    public StaticContextLookup() {
        instance = this;
    }

    /**
     * Return bean that matches name and class.
     * Calls ApplicationContext#getBean.
     * @param name bean name or id
     * @param clazz bean class
     * @param <T> class type
     * @return bean
     */
    public static <T> T getBean( String name, Class<T> clazz ) {
        if ( instance == null ) {
            logger.warn( "could not look up bean: " + name + ". context has not been initialized" );
            return null;
        }
        return instance.context.getBean( name, clazz );
    }
}
