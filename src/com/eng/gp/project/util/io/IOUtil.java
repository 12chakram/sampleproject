package com.eng.gp.project.util.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.net.URL;

import java.util.Properties;

import org.apache.log4j.Logger;

public class IOUtil {

    private static final Logger log = Logger.getLogger( IOUtil.class );

    /**
     * Copys all bytes from the input stream to the output
     * stream.
     *
     * Quick implementation.  Can be changed to have configurable
     * buffer size or use nio channels in the future.
     *
     * @param in  the input stream to read from.
     * @param out the output stream to write to.
     *
     * @exception IOException thrown if the data cannot be read or written.
     */
    public static void copy( InputStream in, OutputStream out )
            throws IOException {

        int read = 0;
        byte[] buffer = new byte[512];

        while ( (read = in.read( buffer )) > 0 ) {
            out.write( buffer, 0, read );
        }
    }

    public static void copy( File in, OutputStream out )
            throws IOException {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream( in );

            copy( fis, out );
        } finally {
            if ( fis != null ) {
                try {
                    fis.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    fis = null;
                }
            }
        }
    }

    public static void copy( URL in, OutputStream out )
            throws IOException {

        InputStream fis = null;
        try {
            fis = in.openStream();

            copy( fis, out );
        } finally {
            if ( fis != null ) {
                try {
                    fis.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    fis = null;
                }
            }
        }
    }

    public static void copy( InputStream in, File out )
            throws IOException {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream( out );
            copy( in, fos );
        } finally {
            if ( fos != null ) {
                try {
                    fos.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    fos = null;
                }
            }
        }
    }

    public static void copy( URL in, File out )
            throws IOException {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream( out );
            copy( in, fos );
        } finally {
            if ( fos != null ) {
                try {
                    fos.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    fos = null;
                }
            }
        }
    }

    public static void copy( File in, File out )
            throws IOException {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream( in );

            copy( fis, out );
        } finally {
            if ( fis != null ) {
                try {
                    fis.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    fis = null;
                }
            }
        }
    }

    /**
     * Returns the contents of the file.
     * Reads the file one byte at a time, not very efficient.
     *
     * @param file the file to get the contents from.
     *
     * @return an array of bytes with the contents of the file.  Will
     *         return a zero length array if the file is empty.
     *
     * @exception IOException thrown if the file cannot be read.
     */
    public static byte[] getContent( File file ) throws IOException {
        FileInputStream in = null;

        try {
            in = new FileInputStream( file );

            return getContent( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }

    public static byte[] getContent( InputStream in ) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy( in, out );

        return out.toByteArray();
    }

    public static String getContentAsString( InputStream in )
            throws IOException {

        return new String( getContent( in ) );
    }

    public static byte[] getResource( String filename ) throws IOException {
        InputStream in = null;

        try {
            in = filename.getClass().getResourceAsStream( filename );

            return getContent( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }

      public static byte[] getResource( String filename, Class clazz ) throws IOException {
        InputStream in = null;

        try {
            in = clazz.getResourceAsStream( filename );

            return getContent( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }

    public static String getResourceAsString( String filename )
            throws IOException {
        return getResourceAsString( filename, filename.getClass() );
    }

    public static String getResourceAsString( String filename, Class clazz )
            throws IOException {
        InputStream in = null;

        try {
            in = clazz.getResourceAsStream( filename );
            if ( in == null ) {
                throw new IllegalArgumentException( "could not get resource " + filename );
            }

            return getContentAsString( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    log.warn( ex );
                } finally {
                    in = null;
                }
            }
        }
    }

    public static void copyResource( String resourceSrc, File dst )
            throws IOException {
        InputStream in = null;

        try {
            in = resourceSrc.getClass().getResourceAsStream( resourceSrc );

            copy( in, dst );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    log.warn( ex );
                } finally {
                    in = null;
                }
            }
        }

    }

    /**
     * Writes the properties back out to a file.
     *
     * @param file       the file to write the properties to.
     * @param properties the properties to persist.
     *
     * @exception IOException thrown if the file cannot be
     *                     written to.
     */
    public static void store( File file, Properties properties )
            throws IOException {

        OutputStream out = null;
        try {
            out = new FileOutputStream( file );
            properties.store( out, null );
        } finally {
            if ( out != null ) {
                try {
                    out.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    out = null;
                }
            }
        }
    }

    /**
     * Loads properties from a given file.
     *
     * @param file the classpath to load the properties from.
     * @return the properties loaded from the specified file.
     * @exception IOException thrown if the file cannot be
     *                     read.
     */
    public static Properties loadProperties( String file )
            throws IOException {
        return loadProperties( file, file.getClass() );
    }

    /**
     * Loads properties from a given file.
     *
     * @param file the classpath to load the properties from
     * @param clazz the class to use to find the resource
     * @return the properties loaded from the specified file.
     * @exception IOException thrown if the file cannot be
     *                     read.
     */
    public static Properties loadProperties( String file, Class clazz )
            throws IOException {

        InputStream in = null;

        try {
            in = clazz.getResourceAsStream( file );
            if ( in == null ) {
                throw new IllegalArgumentException( "could not get property resource " + file );
            }

            return loadProperties( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }

    /**
     * Loads properties from a given file.
     *
     * @param file the file to load the properties from.
     *
     * @return the properties loaded from the specified file.
     *
     * @exception IOException thrown if the file cannot be
     *                     read.
     */
    public static Properties loadProperties( File file )
            throws IOException {

        InputStream in = null;

        try {
            in = new FileInputStream( file );
            return loadProperties( in );
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException ex ) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }

    /**
     * Loads properties from a given InputStream.
     *
     * @param in the InputStream to load the properties from.
     *
     * @return the properties loaded from the specified file.
     *
     * @exception IOException thrown if the file cannot be
     *                     read.
     */
    public static Properties loadProperties( InputStream in )
        throws IOException {

        Properties properties = new Properties();
        properties.load( in );

        return properties;
    }
}