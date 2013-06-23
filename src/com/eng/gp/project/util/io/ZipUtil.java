package com.eng.gp.project.util.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;


import org.apache.log4j.Logger;

public class ZipUtil {

    private static final Logger log = Logger.getLogger(ZipUtil.class);

    public static void zip(File dir, String name, File ... files) 
        throws IOException {
        File jar = new File(dir, name);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(jar);
            JarOutputStream writer = new JarOutputStream(out);

            for (File file : files) {
                ZipEntry entry = new ZipEntry(file.getName());
                writer.putNextEntry(entry);
                IOUtil.copy(file, writer);
                writer.closeEntry();
            }
            writer.finish();
            writer.close();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    log.warn(ex);
                } finally {
                    out = null;
                }
            }
        }
    }

    /**
     * Parse the manifest from a given jar file.
     * 
     * @param file the jar file to read the manifest from.
     *
     * @return the manifest of the given jar file as a
     * collection of properties.
     *
     * @exception IOException thrown if the jar file or it's
     * manifest cannot be read.
     */
    public static Properties getManifest(File file) 
        throws IOException {

        ZipInputStream in = null;
        ZipEntry entry = null;
        try {
            in = new ZipInputStream(new FileInputStream(file));
            
            while ((entry = in.getNextEntry()) != null) {
                if ("META-INF/MANIFEST.MF".equalsIgnoreCase(entry.getName())) {
                    Properties properties = new Properties();
                    int c;
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    while ((c = in.read()) != -1) {
                        if (c == '\n' || c == '\r') {
                            while (c == '\n' || c == '\r') {
                                c = in.read();
                            }
                            
                            if (Character.isWhitespace(c)) {
                                while (Character.isWhitespace(c)) {
                                    c = in.read();
                                }
                            } else {
                                out.write('\n');
                            }
                        }
                        
                        if (c != -1) {
                            out.write(c);
                        }
                    }
                    
                    properties.load(new ByteArrayInputStream(out.toByteArray()));
        
                    return properties;
                }
            }

            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
    }
}