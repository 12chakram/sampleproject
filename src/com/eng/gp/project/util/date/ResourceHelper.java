package com.eng.gp.project.util.date;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

/**
 * Utilities for working with package resources.
 */
public class ResourceHelper {
    /**
     * Obtains a resource and converts it to a byte array.
     *
     * @param name The name of the resource
     * @return the content of the resource as a byte array.  If no such resource exists then {@code null} is returned.
     * @throws java.io.IOException the resource could not be read properly
     */
    public byte[] getResourceAsByteArray(String name) throws IOException {
        final int INCREMENT = 1024;

        /**
         * The size of the resource in bytes.
         */
        int size = 0;

        // Read the bytes of the stream.  Since we do not know the total stream size, read it in chunks.
        LinkedList<byte[]> chunks = new LinkedList<byte[]>();
        InputStream stream = null;
        try {
            // Get the resource for the input stream.
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            if (stream == null) {
                return null;
            }

            // Read the stream in chunks.
            int lastReadSize;
            do {
                byte[] chunk = new byte[INCREMENT];
                lastReadSize = stream.read(chunk, 0, INCREMENT);
                size += lastReadSize > 0 ? lastReadSize : 0;
                chunks.add(chunk);
            } while (lastReadSize == INCREMENT);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // Concatenate the chunks into our result---a single byte array.
        byte[] content = new byte[size];
        int bytesLeft = size;
        for (byte[] chunk : chunks) {
            System.arraycopy(chunk, 0, content, size - bytesLeft, Math.min(bytesLeft, chunk.length));
            bytesLeft -= Math.min(chunk.length, bytesLeft);
        }
        return content;
    }
}
