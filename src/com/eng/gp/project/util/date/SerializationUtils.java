package com.eng.gp.project.util.date;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.HexDump;
import org.apache.log4j.Logger;

/**
 * Serialization Utils.
 */
public class SerializationUtils
{
    private static final Logger logger = Logger.getLogger( SerializationUtils.class );

    /**
     * Deserialize a java Object that used gzip'd Java Serialization.
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object decodeCompressed( final byte[] bytes )
        throws Exception
    {
        ByteArrayInputStream inBytes     = new ByteArrayInputStream( bytes );
        GZIPInputStream      gzipIn      = new GZIPInputStream( inBytes );
        ObjectInputStream    inObject    = new ObjectInputStream( gzipIn );
        return( inObject.readObject() );
    }

    /**
     * Serialize a Java Object and then Compress the Byte Stream with GZIP.
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeCompressed( final Object data )
        throws Exception
    {
        ByteArrayOutputStream outBytes    = new ByteArrayOutputStream();
        GZIPOutputStream      gzipOut     = new GZIPOutputStream( outBytes );
        ObjectOutputStream    outObject   = new ObjectOutputStream( gzipOut );
        outObject.writeObject( data );
        outObject.close();
        return( outBytes.toByteArray() );
    }

    /**
     * Standard Java Deserialization
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object decode( final byte[] bytes )
        throws Exception
    {
        ByteArrayInputStream    inBytes     = new ByteArrayInputStream( bytes );
        ObjectInputStream       inObject    = new ObjectInputStream( inBytes );
        return( inObject.readObject() );
    }

    /**
     * Standard Java Serialization
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encode( final Object data )
        throws Exception
    {
        ByteArrayOutputStream   outBytes    = new ByteArrayOutputStream();
        ObjectOutputStream      outObject   = new ObjectOutputStream( outBytes );
        outObject.writeObject( data );
        outObject.close();
        return( outBytes.toByteArray() );
    }

    /**
     * Serialize an Object as XML.
     * @param object
     * @return
     */
    public static String encodeAsXml( final Object object )
    {
        ByteArrayOutputStream   outBytes    = new ByteArrayOutputStream();
        XMLEncoder              outObject   = new XMLEncoder( outBytes );
        outObject.writeObject( object );
        outObject.close();
        return( outBytes.toString() );
    }

    /**
     * Deserialize an Object from Xml.
     * @param xmlObject
     * @return
     * @throws Exception
     */
    public static Object decodeFromXml( final String xmlObject )
        throws Exception
    {
        ByteArrayInputStream    inBytes     = new ByteArrayInputStream( xmlObject.getBytes() );
        XMLDecoder              decoder     = new XMLDecoder( inBytes );
        return( decoder.readObject() );
    }

    /**
     * HexDump a byte array
     * @param rawBytes
     * @return
     */
    public static String hexDump( final byte[] rawBytes )
    {
        if( rawBytes == null  ||  rawBytes.length == 0 )
        {
            return( "NULL" );
        }
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();

        try
        {
            HexDump.dump( rawBytes, 0, bytesOut, 0 );
        }catch( Exception ex )
        {
            logger.error("Hex Dump error",ex);
        }

        return( bytesOut.toString() );
    }
}
