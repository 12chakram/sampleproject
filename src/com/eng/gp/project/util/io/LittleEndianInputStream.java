package com.eng.gp.project.util.io;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * Little endian version of DataInputStream.
 * DataInputStream has a large number of final methods,
 * so overriding just the methods we care about
 * would not work.
 */
public class LittleEndianInputStream implements DataInput {
    /** Longest block of bytes we ever need to read. */
    private byte[] buffer = new byte[8];

    /** The underlying input stream. */
    private DataInputStream in;

    /**
     * Constructs a new <code>LittleEndianInputStream</code>
     * used to read data store in little endian.
     *
     * @param in the input stream to read bytes from.
     */
    public LittleEndianInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final void readFully(byte ba[]) throws IOException {
        in.readFully(ba, 0, ba.length);
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final void readFully(byte ba[], int off, int len ) throws IOException {
        in.readFully(ba, off, len);
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final int skipBytes(int n) throws IOException {
        return in.skipBytes(n);
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final String readUTF() throws IOException {
        return in.readUTF();
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    /**
     * Same as DataInputStream
     */
    @Override
    @SuppressWarnings("deprecation")
    public final String readLine() throws IOException {
        return in.readLine();
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final boolean readBoolean() throws IOException {
        return in.readBoolean();
    }

    /**
     * Same as DataInputStream
     */
    @Override
    public final byte readByte() throws IOException {
        return in.readByte();
    }

    /**
     * Same as DataInputStream
     */
    public final int available() throws IOException {
        return in.available();
    }

    /**
     * Read a long and convert it to a double.
     */
    @Override
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    /**
     * Read an int and convert it to a float.
     */
    @Override
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    /**
     * Read an int (backwards).
     */
    @Override
    public final int readInt() throws IOException {
        readFully(buffer, 0, 4);
        return (buffer[3]) << 24
            | (buffer[2] & 0xff) << 16
            | (buffer[1] & 0xff) << 8
            | (buffer[0] & 0xff);
    }

    /**
     * Read a long (backwards).
     */
    @Override
    public final long readLong() throws IOException {
        readFully( buffer, 0, 8 );

        // long cast needed or shift done modulo 32
        return (long) (buffer[7]) << 56 
            | (long) (buffer[6] & 0xff) << 48
            | (long) (buffer[5] & 0xff) << 40
            | (long) (buffer[4] & 0xff) << 32
            | (long) (buffer[3] & 0xff) << 24
            | (long) (buffer[2] & 0xff) << 16
            | (long) (buffer[1] & 0xff) << 8
            | (long) (buffer[0] & 0xff);
    }

    @Override
    public final int readUnsignedShort() throws IOException {
        readFully(buffer, 0, 2);
        return ((buffer[1] & 0xff) << 8 | (buffer[0] & 0xff));
    }

    /**
     * Read a short (backwards).
     */
    @Override
    public final short readShort() throws IOException {
        return (short)readUnsignedShort();
    }

    @Override
    public final char readChar() throws IOException {
        return (char)readUnsignedShort();
    }
}