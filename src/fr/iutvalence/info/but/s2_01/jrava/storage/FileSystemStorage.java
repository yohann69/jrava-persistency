package fr.iutvalence.info.but.s2_01.jrava.storage;

import fr.iutvalence.info.but.s2_01.jrava.model.Activity;
import fr.iutvalence.info.but.s2_01.jrava.model.ActivityType;
import fr.iutvalence.info.but.s2_01.jrava.model.Storage;
import fr.iutvalence.info.but.s2_01.jrava.model.StorageAccessException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * File system implementation of storage service.
 * All activities are supposed to be stored as separate files
 * (named with activity id, with .jrv extension), in a same directory.
 *
 */
public class FileSystemStorage implements Storage {

    //constructor
    public FileSystemStorage(File directory) {
        getNextId();
    }
    @Override
    public int getNextId() {
        // Generated skeleton, to be completed
        return 0;
    }

    @Override
    public Map<Integer, Activity> loadActivities() throws StorageAccessException {
        // Generated skeleton, to be completed
        return null;
    }

    @Override
    public int saveNewActivity(Activity activity) throws StorageAccessException {
        // Generated skeleton, to be completed
        return 0;
    }

    @Override
    public void updateActivity(int activityId, Activity activity) throws StorageAccessException {
        // Generated skeleton, to be completed
    }
}
