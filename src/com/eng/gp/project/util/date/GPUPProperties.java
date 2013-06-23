package com.eng.gp.project.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Structured property bag for GPUP system properties.
 *
 * The GPUPProperties object has a couple properties
 * that are derived from other properties.
 *
 * Product version corresponds to version in JIRA, and is
 * composed of mavenVersion and svnVersion as follows:
 *   &lt;major&gt;.&lt;minor&gt;.&lt;bug-fix&gt;.&lt;svn-version&gt;
 * The mavenVersion makes up the major, minor, and bug-fix
 * portions (and may include svn-version if released).  If
 * the mavenVersion has "-SNAPSHOT", then it hasn't been released,
 * and the product version is made up of the mavenVersion with
 * "-SNAPSHOT" replaced and svnVersion appended.
 *
 * buildDateString is just derived from taking the long
 * buildDate and converting it to a date using:
 * <code>( new Date( buildDate ) ).toString()</code>
 */
public class GPUPProperties extends Properties {

    private static final long serialVersionUID = 123334L;

    public void setMavenVersion( String mavenVersion ) {
        this.setProperty( "mavenVersion", mavenVersion );
        initializeProductVersion();
    }

    public void setSvnVersion( String svnVersion ) {
        this.setProperty( "svnVersion", svnVersion );
        initializeProductVersion();
    }

    public void setBuildDate( String buildDate ) {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd-HHmm").parse(buildDate);
            this.setProperty( "buildDate", String.valueOf(date.getTime()) );
            this.setProperty( "buildDateString", date.toString() );
        } catch(Exception ex) {
            ex.printStackTrace();
            this.setProperty( "buildDate", "-1" );
            this.setProperty( "buildDateString", "UNKNOWN" );
        }
    }

    // Setup product version from mavenVersion and svnVersion
    private void initializeProductVersion() {
        String mavenVersion = this.getProperty( "mavenVersion" );
        String svnVersion = this.getProperty( "svnVersion" );
        String projectVersion = "0.0.0";
        if ( mavenVersion != null && svnVersion != null ) {
            if ( mavenVersion.matches( ".*-SNAPSHOT" ) ) {
                projectVersion = mavenVersion.replaceAll( "-SNAPSHOT", "" ) + "." + svnVersion;
            } else {
                projectVersion = mavenVersion;
            }

        }
        this.put( "productVersion", projectVersion );
    }

    public void setProperties( Properties properties ) {
        this.putAll( properties );
    }

}
