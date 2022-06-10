package fr.iutvalence.info.but.s2_01.jrava.model;

/**
 * General contract for Man-Machine Interface.
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public interface Mmi {

    /**
     * Sets activity tracker.
     *
     * @param activityTracker activity tracker
     */
    void setActivityTracker(ActivityTracker activityTracker);

    /**
     * Displays MMI.
     */
    void displayMMI();
}