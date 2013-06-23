package com.eng.gp.project.util.date;

import com.eng.gp.project.util.io.IOUtil;

import org.apache.catalina.util.DateTool;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.util.Map;
import java.util.UUID;

public class TemplateUtil {

    private static final String REPO_NAME = "templateServiceStringLoader";

    @Autowired
    private static final VelocityEngine velocityEngine;

    static {
        try {
            velocityEngine = new VelocityEngine( 
                    IOUtil.loadProperties( "/velocity.properties", TemplateUtil.class ) );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    private static final Logger logger = Logger.getLogger( TemplateUtil.class );

    public String templateReplaceString( String template, Map<String, Object> contextData ) {
        // Create temporary name that won't conflict
        String templateName = UUID.randomUUID().toString();

        StringResourceRepository repo =
                (StringResourceRepository) velocityEngine.getApplicationAttribute( REPO_NAME );
        if ( repo == null ) {
            throw new RuntimeException( "Could not access StringResourceRepository for template replace" );
        }

        // Add template to repo
        try {
            repo.putStringResource( templateName, template );
            return templateReplace( templateName, contextData );
        } finally {
            repo.removeStringResource( templateName );
        }

    }

    public String templateReplace( String templateName, Map<String, Object> contextData ) {
        return templateReplace( templateName, null, contextData );
    }

    public String templateReplace( String templateName, String defaultTemplateName,
                                   Map<String, Object> contextData ) {
        Template template = getTemplate( templateName, defaultTemplateName );

        StringWriter resultWriter = new StringWriter( 100 );
        VelocityContext velocityContext = new VelocityContext();

        contextData.put( "dateTool", new DateTool() );

        for ( Map.Entry<String, Object> entry : contextData.entrySet() ) {
            velocityContext.put( entry.getKey(), entry.getValue() );
        }

        try {
            template.merge( velocityContext, resultWriter );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }

        return resultWriter.toString();
    }

    private Template getTemplate( String templateName ) {
        Template template = null;
        try {
            template = velocityEngine.getTemplate( templateName );
        } catch ( ResourceNotFoundException e ) {
            if ( logger.isInfoEnabled() ) {
                logger.info( "template: " + templateName + " could not be found" );
            }
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }

        return template;
    }

    private Template getTemplate( String templateName, String defaultTemplateName ) {
        Template template = getTemplate( templateName );
        if ( template == null && defaultTemplateName != null ) {
            template = getTemplate( defaultTemplateName );
        }
        return template;
    }

}
