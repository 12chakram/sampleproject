package com.eng.gp.project.util.date;

import java.io.File;

/**
 * File/directory helper methods
 */
public class FileUtil {

    /**
     * Recursive directory delete
     * @param dir to delete
     * @return true if delete succeeds
     */
    public static boolean deleteDir( File dir ) {
        if ( dir.isDirectory() ) {
            String[] children = dir.list();
            for ( String child : children ) {
                boolean success = deleteDir( new File( dir, child ) );
                if ( !success ) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
}
