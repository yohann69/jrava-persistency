package fr.iutvalence.info.but.s2_01.jrava.storage;

import fr.iutvalence.info.but.s2_01.jrava.model.Storage;

import java.io.File;
import java.io.FilenameFilter;

/**
 * File name filter for Jrava files.
 *
 */
public class JravaFileNameFilter implements FilenameFilter {

    public static final String JRAVA_FILE_EXTENSION = ".jrv";

    public JravaFileNameFilter( File dir){


    }


    @Override
    public boolean accept(File dir, String name) {
        if (dir.getName().endsWith(JRAVA_FILE_EXTENSION)) {
            return true;
        }else{
            return false;
        }
    }
}
