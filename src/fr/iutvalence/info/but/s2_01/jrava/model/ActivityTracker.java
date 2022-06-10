package fr.iutvalence.info.but.s2_01.jrava.model;

import fr.iutvalence.info.but.s2_01.jrava.storage.NoStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * Activity tracker.
 * Activities are cached in memory, and also stored/retrieved using a pluggable storage service.
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class ActivityTracker {

    /**
     * Activity cache.
     */
    private Map<Integer, Activity> activities;

    /**
     * Pluggable storage service.
     */
    private Storage storage;

    /**
     * Creates a new activity tracker, with a "no storage" service and initially no activity.
     */
    public ActivityTracker() {
        this.activities = new HashMap<>();
        this.storage = new NoStorage();
    }

    /**
     * Sets the storage service to be used.
     * N.B. activity cache is erased, activities are reloaded using storage service
     *
     * @param storage storage service to use
     * @throws StorageAccessException if storage access failed while reloading activities
     */
    public void setStorage(Storage storage) throws StorageAccessException {
        this.storage = storage;
        this.reloadActivities();
    }

    /**
     * Reloads activities (in cache) from storage service.
     *
     * @throws StorageAccessException if storage access failed while reloading activities
     */
    private void reloadActivities() throws StorageAccessException {
        this.activities = this.storage.loadActivities();
    }

    /**
     * Adds a new activity in cache, also saved using storage service.
     *
     * @param activity activity
     * @return activity id, or <i>-1</i> if activity could not be saved
     */
    public int addActivity(Activity activity) {
        int activityId = -1;
        try {
            activityId = this.storage.saveNewActivity(activity);
            this.activities.put(activityId, activity);
        } catch (StorageAccessException e) {
            // Nothing to do here, -1 will be returned as id
        }

        return activityId;
    }

    /**
     * Returns statistical data, for a given activity type.
     *
     * @param type activity type
     * @return statistical data for given activity type
     */
    public ActivityStats getStats(ActivityType type) {
        ActivityFilter filter = new ActivityFilter(this.activities);
        Map<Integer, Activity> filtered = filter.filterByType(type);
        return new ActivityStats(filtered);
    }

    /**
     * Returns statistical data, for a all activities.
     *
     * @return statistical data, for all activities
     */
    public ActivityStats getStats() {
        return new ActivityStats(activities);
    }

    /**
     * Returns cached activity count.
     *
     * @return activity count
     */
    public int getActivityCount() {
        return this.activities.size();
    }

    /**
     * Returns a copy of all cached activities.
     *
     * @return a copy of all cached activities
     */
    public Map<Integer, Activity> getActivities() {
        Map<Integer, Activity> result = new HashMap<>();
        result.putAll(this.activities);
        return result;
    }

    /**
     * Returns a copy of all cached activities of given type.
     *
     * @param type activity type
     * @return a copy of all cached activities of given type
     */
    public Map<Integer, Activity> getActivitiesByType(ActivityType type) {
        ActivityFilter filter = new ActivityFilter(this.activities);
        return filter.filterByType(type);
    }

    /**
     * Returns a cached activity from its given id, if it exists.
     *
     * @param id activity id
     * @return activity of given id if it exists, <i>null</i> else
     */
    public Activity getActivityById(int id) {
        return this.activities.get(id);
    }

    /**
     * Updates title of an activity, given its id.
     *
     * @param activityId activity id
     * @param title      new title
     * @throws StorageAccessException if storage access failed while saving activity
     */
    public void updateActivityTitle(int activityId, String title) throws StorageAccessException {
        Activity activity = this.getActivityById(activityId);
        if (activity == null)
            return;
        activity.setTitle(title);
        this.storage.updateActivity(activityId, activity);
    }

    /**
     * Updates description of an activity, given its id.
     *
     * @param activityId  activity id
     * @param description new description
     * @throws StorageAccessException if storage access failed while saving activity
     */
    public void updateActivityDescription(int activityId, String description) throws StorageAccessException {
        Activity activity = this.getActivityById(activityId);
        if (activity == null)
            return;
        activity.setDescription(description);
        this.storage.updateActivity(activityId, activity);
    }
}
