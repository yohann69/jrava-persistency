package fr.iutvalence.info.but.s2_01.jrava.model;

import java.util.Map;

/**
 * General contract for storage service.
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public interface Storage {
    /**
     * Returns next activity id.
     *
     * @return next activity id
     */
    int getNextId();

    /**
     * Loads all stored activities.
     *
     * @return a list containing all stored activities
     * @throws StorageAccessException if storage access failed while reloading activities
     */
    Map<Integer, Activity> loadActivities() throws StorageAccessException;

    /**
     * Saves a new activity.
     *
     * @param activity activity to be saved
     * @return saved activity id
     * @throws StorageAccessException if storage access failed while saving activity
     */
    int saveNewActivity(Activity activity) throws StorageAccessException;

    /**
     * Updates a previously saved activity.
     *
     * @param activityId activity id
     * @param activity   activity to be saved
     * @throws StorageAccessException if storage access failed while saving activity
     */
    void updateActivity(int activityId, Activity activity) throws StorageAccessException;
}
