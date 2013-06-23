package com.eng.gp.project.util.date;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * Wraps an {@link InputStream} and copies bytes to an {@link OutputStream} as they are read.
 * 
 * @author DWC
 * 
 */
public class DebuggingInputStream extends FilterInputStream {
    private OutputStream debugOut;

    public DebuggingInputStream(InputStream in, OutputStream debugOut) {
        super(in);
        this.debugOut = debugOut;
    }

    public int read() throws IOException {
        int c = super.read();
        if (-1 != c) {
            debugOut.write((char) c);
        }
        return c;
    }

    public int read(byte[] b) throws IOException {
        int readCount = super.read(b, 0, b.length);
        debugOut.write(b, 0, readCount);
        return readCount;
    }

    public int read(byte[] b, int offset, int length) throws IOException {
        int readCount = super.read(b, offset, length);
        debugOut.write(b, offset, readCount);
        return readCount;
    }

    /**
     * Read the remainder of bytes from the {@link InputStream} into the {@OutputStream} without waiting
     * for the bytes to be read. This may be useful to capture the remainder of an InputStream after a problem has been
     * encountered during processing.
     * 
     * @throws IOException
     */
    public void readRemainder() throws IOException {
        IOUtils.copy(in, debugOut);
    }
}
