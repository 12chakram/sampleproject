package com.eng.gp.project.util.date;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.security.core.codec.Hex;
//import java.math.BigInteger;

/**
 *
 */
public class DigestUtil {
	private static final Logger logger = Logger.getLogger( DigestUtil.class );

	private static final int POSTIVE_SIGN = 1;
	private static final int HEXIDECIMAL = 16;

    public static String md5(String string){
        return md5(string.getBytes());
    }
    public static String md5(byte[] bytes){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        String hexMD5 = new String(Hex.encode(md.digest(bytes)));
        return hexMD5;
    }

    public static byte[] computeDigestAsBytes( byte[] data )
    {
        try
        {
        	// note using MD5 as algorithm
        	MessageDigest messageDigest = MessageDigest.getInstance( "MD5" );
            return( messageDigest.digest( data ) );
        }
        catch ( NoSuchAlgorithmException e )
        {
            logger.error( "MessageDigest initialization failed", e );
            throw new ExceptionInInitializerError( e );
        }

    }
    public static long splitDigestLow( final byte[] data )
    {
        long digest = 0;
        digest |= ((long)data[0]);
        digest |= (((long)data[1]) << 8);
        digest |= (((long)data[2]) << 16);
        digest |= (((long)data[3]) << 24);

        digest |= (((long)data[4]) << 32);
        digest |= (((long)data[5]) << 40);
        digest |= (((long)data[6]) << 48);
        digest |= (((long)data[7]) << 56);

        return( digest );
    }
    public static long splitDigestHigh( final byte[] data )
    {
        long digest = 0;
        digest |= ((long)data[8]);
        digest |= (((long)data[9]) << 8);
        digest |= (((long)data[10]) << 16);
        digest |= (((long)data[11]) << 24);

        digest |= (((long)data[12]) << 32);
        digest |= (((long)data[13]) << 40);
        digest |= (((long)data[14]) << 48);
        digest |= (((long)data[15]) << 56);

        return( digest );
    }
}
