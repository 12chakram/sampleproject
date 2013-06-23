package com.eng.gp.project.util.io;

import org.apache.log4j.Logger;

import java.util.*;
import java.io.*;

import au.com.bytecode.opencsv.CSVReader;

public class CsvUtil {
    private static final Logger logger = Logger.getLogger( CsvUtil.class );

    public static List<List<String>> readLinesFromFile( String fileName ) {
        List<List<String>> lines = null;
        try {
            lines = readLinesFromInputStream(new FileInputStream(new File(fileName)));
        } catch (IOException e) {
            logger.warn( "file: '" + fileName + "' could not be opened", e );
        }
        return lines;
    }

    public static List<List<String>> readLinesFromResource( String resourceName ) {
        List<List<String>> lines = null;
        try {
            lines = readLinesFromInputStream(Object.class.getResourceAsStream(resourceName));
        } catch (IOException e) {
            logger.warn( "resource file: '" + resourceName + "' could not be opened", e );
        }
        return lines;
    }

    private static List<List<String>> readLinesFromInputStream(InputStream inputStream) throws IOException {

        List<List<String>> lines = null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try {
            BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
            CSVReader reader = new CSVReader( bufferedReader );
            lines = new ArrayList<List<String>>();
            String strings[];
            while ( (strings = reader.readNext()) != null ) {
                lines.add( Arrays.asList( strings ) );
            }
            inputStream.close();
        } finally {
            if ( inputStream != null ) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.warn(ex);
                } finally {
                    inputStream = null;
                }
	        }
        }
        return lines;
    }

    /**
     * Writes the Strings in lines to a file
     *
     * @param fileName file to which to write
     * @param lines    an array of Strings
     */
    public static void writeLines( String fileName, List<String> lines ) {
        File file = new File( fileName );
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter( file );
            BufferedWriter writer = new BufferedWriter( fileWriter );
            for ( String line : lines ) {
                writer.write( line );
                writer.newLine();
            }
            writer.close();
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        finally {
            if ( fileWriter != null ) {
                try {
                    fileWriter.close();
                }
                catch ( IOException e ) {
                    logger.warn( e );
                }
                finally {
                    fileWriter = null;
                }
            }
        }
    }
}
