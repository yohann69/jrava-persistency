package fr.iutvalence.info.but.s2_01.jrava.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Activity filter, initialized with an activity list, and allowing to filter this list by type,
 * year (current), month (current).
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class ActivityFilter {

    /**
     * Activities to filter.
     */
    private final Map<Integer, Activity> allActivities;

    /**
     * Calendar used to match activity dates against current date.
     */
    private final Calendar currentDate;

    /**
     * Creates a new activity filter, for given activities.
     *
     * @param activities activities to filter
     */
    public ActivityFilter(Map<Integer, Activity> activities) {
        this.allActivities = new HashMap<>();
        this.allActivities.putAll(activities);
        this.currentDate = Calendar.getInstance();
    }

    /**
     * Filters activities to retain only those of a given type.
     *
     * @param activityType activity type
     * @return only activities of given type
     */
    public Map<Integer, Activity> filterByType(ActivityType activityType) {
        Map<Integer, Activity> result = new HashMap<>();
        for (Map.Entry<Integer, Activity> entry : this.allActivities.entrySet()) {
            if (entry.getValue().getType() == activityType)
                result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Filters activities to retain those of current year.
     *
     * @return only activities dated by current year
     */
    public Map<Integer, Activity> filterByCurrentYear() {
        Map<Integer, Activity> result = new HashMap<>();
        for (Map.Entry<Integer, Activity> entry : this.allActivities.entrySet()) {
            if (isCurrentYear(entry.getValue().getDate()))
                result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Filters activities to retain those of current month.
     *
     * @return a list containing only activities dated by current month
     */
    public Map<Integer, Activity> filterByCurrentMonth() {
        Map<Integer, Activity> result = new HashMap<>();
        for (Map.Entry<Integer, Activity> entry : this.allActivities.entrySet()) {
            if (isCurrentMonth(entry.getValue().getDate()))
                result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Checks if a given year matches current one.
     *
     * @param date date to check
     * @return <i>true</i> if year is current one, <i>false</i> else
     */
    private boolean isCurrentYear(Calendar date) {
        currentDate.setTimeInMillis(System.currentTimeMillis());
        return (date.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR));
    }

    /**
     * Checks if a given month matches current one.
     *
     * @param date date to check
     * @return <i>true</i> if month is current one, <i>false</i> else
     */
    private boolean isCurrentMonth(Calendar date) {
        currentDate.setTimeInMillis(System.currentTimeMillis());
        return (date.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH));
    }
}
