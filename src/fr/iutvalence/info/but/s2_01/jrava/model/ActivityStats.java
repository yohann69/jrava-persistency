package fr.iutvalence.info.but.s2_01.jrava.model;

import java.util.Collection;
import java.util.Map;

/**
 * Statistical data about activities (global/yearly/monthly count/duration/distance).
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class ActivityStats {
    /**
     * Global count (all activities).
     */
    private final int globalCount;

    /**
     * Yearly count.
     */
    private final int yearlyCount;

    /**
     * Monthly count.
     */
    private final int monthlyCount;

    /**
     * Yearly duration.
     */
    private final long yearlyDuration;

    /**
     * Monthly duration.
     */
    private final long monthlyDuration;

    /**
     * Yearly distance.
     */
    private final double yearlyDistance;

    /**
     * Monthly distance.
     */
    private final double monthlyDistance;

    /**
     * Creates new statistical data, given all metrics.
     *
     * @param globalCount     global count
     * @param yearlyCount     yearly count
     * @param yearlyDuration  yearly duration
     * @param yearlyDistance  yearly distance
     * @param monthlyCount    monthly count
     * @param monthlyDuration monthly duration
     * @param monthlyDistance monthly distance
     */
    public ActivityStats(int globalCount, int yearlyCount, int yearlyDuration, double yearlyDistance, int monthlyCount, int monthlyDuration, double monthlyDistance) {
        this.globalCount = globalCount;
        this.yearlyCount = yearlyCount;
        this.yearlyDuration = yearlyDuration;
        this.yearlyDistance = yearlyDistance;
        this.monthlyCount = monthlyCount;
        this.monthlyDuration = monthlyDuration;
        this.monthlyDistance = monthlyDistance;
    }

    /**
     * Creates new statistical data, computed from a given activity list.
     *
     * @param activities activity list used to compute statistical data
     */
    public ActivityStats(Map<Integer, Activity> activities) {
        this.globalCount = activities.size();

        ActivityFilter filter = new ActivityFilter(activities);
        Map<Integer, Activity> filtered = filter.filterByCurrentYear();
        this.yearlyCount = filtered.size();
        this.yearlyDuration = this.getTotalDuration(filtered.values());
        this.yearlyDistance = this.getTotalDistance(filtered.values());

        filtered = filter.filterByCurrentMonth();
        this.monthlyCount = filtered.size();
        this.monthlyDuration = this.getTotalDuration(filtered.values());
        this.monthlyDistance = this.getTotalDistance(filtered.values());
    }

    /**
     * Returns total duration (in seconds) for given activities.
     *
     * @param activities activities
     * @return sum of activity durations
     */
    private int getTotalDuration(Collection<Activity> activities) {
        int totalDuration = 0;
        for (Activity activity : activities) {
            totalDuration += activity.getDuration();
        }
        return totalDuration;
    }

    /**
     * Returns total distance (in meters) for given activities.
     *
     * @param activities activities
     * @return sum of activity distances
     */
    private double getTotalDistance(Collection<Activity> activities) {
        int totalDistance = 0;
        for (Activity activity : activities) {
            totalDistance += activity.getDistance();
        }
        return totalDistance;
    }

    /**
     * Returns global count.
     *
     * @return global count
     */
    public int getGlobalCount() {
        return this.globalCount;
    }

    /**
     * Returns yearly count.
     *
     * @return yearly count
     */
    public int getYearlyCount() {
        return this.yearlyCount;
    }

    /**
     * Returns monthly count.
     *
     * @return monthly count
     */
    public int getMonthlyCount() {
        return this.monthlyCount;
    }

    /**
     * Returns yearly duration (in seconds).
     *
     * @return yearly duration
     */
    public long getYearlyDuration() {
        return this.yearlyDuration;
    }

    /**
     * Returns monthly duration (in seconds).
     *
     * @return monthly duration
     */
    public long getMonthlyDuration() {
        return this.monthlyDuration;
    }

    /**
     * Returns yearly distance (in meters).
     *
     * @return yearly distance
     */
    public double getYearlyDistance() {
        return this.yearlyDistance;
    }

    /**
     * Returns monthly distance (in meters).
     *
     * @return monthly distance
     */
    public double getMonthlyDistance() {
        return this.monthlyDistance;
    }

    @Override
    public String toString() {
        return "ActivityStats{" +
                "globalCount=" + globalCount +
                ", yearCount=" + yearlyCount +
                ", yearDuration=" + yearlyDuration +
                ", yearDistance=" + yearlyDistance +
                ", monthCount=" + monthlyCount +
                ", monthDuration=" + monthlyDuration +
                ", monthDistance=" + monthlyDistance +
                '}';
    }
}
