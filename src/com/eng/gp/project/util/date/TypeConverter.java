package com.eng.gp.project.util.date;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.LongBuffer;

public class TypeConverter {
    /**
     * Convert a long to a byte array.
     *
     * @param l the long to convert
     *
     * @return a byte array representing the long.
     */
    public static byte[] longToByteArray(long l) {
        byte[] buffer = new byte[8];

        ByteBuffer bBuffer = ByteBuffer.wrap(buffer);
        LongBuffer lBuffer = bBuffer.asLongBuffer();
        lBuffer.put(0, l);
        return buffer;
    }

    /**
     * Converts a byte array to a long.
     *
     * @param buffer a buffer of bytes to convert.
     *
     * @return a long from the given bytes.
     */
    public static long bytesToLong(byte[] buffer) {
       ByteBuffer bBuffer = ByteBuffer.wrap(buffer);
       LongBuffer lBuffer = bBuffer.asLongBuffer();

       return lBuffer.get(0);
    }

    /**
     * Converts a double to a byte array.
     *
     * @param d the double to convert.
     * @return a byte array representing the double.
     */
    public static byte[] doubleToByteArray(double d) {
        byte[] buffer = new byte[8];
        
        ByteBuffer bBuffer = ByteBuffer.wrap(buffer);
        DoubleBuffer dBuffer = bBuffer.asDoubleBuffer();
        dBuffer.put(0, d);
        return buffer;
    }

    /**
     * Converts a byte array to a double.
     *
     * @param buffer a buffer of bytes to convert.
     *
     * @return a double from the given bytes.
     */
    public static double bytesToDouble(byte[] buffer) {
        ByteBuffer bBuffer = ByteBuffer.wrap(buffer);
        DoubleBuffer dBuffer = bBuffer.asDoubleBuffer();
        
        return dBuffer.get(0);
    }
}