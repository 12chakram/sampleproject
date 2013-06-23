package com.eng.gp.project.util.io;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Little endian version of DataOutputStream.
 * DataOutputStream has a large number of final methods,
 * so overriding just the methods we care about
 * would not work.
 */
public class LittleEndianOutputStream implements DataOutput {
    /** Longest block of bytes we ever need to read. */
    private byte[] buffer = new byte[8];

    /** The underlying output stream. */
    private DataOutputStream out;

    /**
     * Constructs a new <code>LittleEndianOutputStream</code>
     * used to read data store in little endian.
     *
     * @param in the output stream to read bytes from.
     */
    public LittleEndianOutputStream(OutputStream in) {
        this.out = new DataOutputStream(in);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void write(int i) throws IOException {
        out.write(i);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void write(byte ba[]) throws IOException {
        out.write(ba);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void write(byte ba[], int off, int len ) throws IOException {
        out.write(ba, off, len);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void writeUTF(String s) throws IOException {
        out.writeUTF(s);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void writeBoolean(boolean b) throws IOException {
        out.writeBoolean(b);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void writeByte(int i) throws IOException {
        out.writeByte(i);
    }

    /**
     * Same as DataOutputStream
     */
    @Override
    public final void writeBytes(String s) throws IOException {
        out.writeBytes(s);
    }

    /**
     * Read a long and convert it to a double.
     */
    @Override
    public final void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToLongBits(d));
    }

    /**
     * Read an int and convert it to a float.
     */
    @Override
    public final void writeFloat(float f) throws IOException {
        writeInt(Float.floatToIntBits(f));
    }

    /**
     * Read an int (backwards).
     */
    @Override
    public final void writeInt(int i) throws IOException {
        buffer[0] = (byte)i;
        buffer[1] = (byte)(i >> 8);
        buffer[2] = (byte)(i >> 16);
        buffer[3] = (byte)(i >> 24);
        write(buffer, 0, 4);
    }

    /**
     * Read a long (backwards).
     */
    @Override
    public final void writeLong(long l) throws IOException {
        buffer[0] = (byte)l;
        buffer[1] = (byte)(l >> 8);
        buffer[2] = (byte)(l >> 16);
        buffer[3] = (byte)(l >> 24);
        buffer[4] = (byte)(l >> 32);
        buffer[5] = (byte)(l >> 40);
        buffer[6] = (byte)(l >> 48);
        buffer[7] = (byte)(l >> 56);
        write(buffer, 0, 8);
    }

    /**
     * Read a short (backwards).
     */
    @Override
    public final void writeShort(int i) throws IOException {
        writeChar(i);
    }

    @Override
    public final void writeChar(int c) throws IOException {
        buffer[0] = (byte)c;
        buffer[1] = (byte)(c >> 8);
        write(buffer, 0, 2);
    }

    @Override
    public final void writeChars(String s) throws IOException {
        for (char c : s.toCharArray()) {
            writeChar(c);
        }
    }

    public final void flush() throws IOException {
        out.flush();
    }


    public final void close() throws IOException {
        out.close();
    }
}