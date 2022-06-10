package fr.iutvalence.info.but.s2_01.jrava.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Activity, with all its data (type, ...).
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class Activity {

    /**
     * Date format (dd/MM/yyyy).
     */
    public final static DateFormat ACTIVITY_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Type.
     */
    private final ActivityType type;

    /**
     * Date.
     */
    private final Calendar date;

    /**
     * Duration (in seconds).
     */
    private final int duration;

    /**
     * Distance (in meters).
     */
    private final double distance;

    /**
     * Title (brief).
     */
    private String title;

    /**
     * Description (optional, can be blank).
     */
    private String description;

    /**
     * Creates a new activity, given all its data (supposed valid).
     *
     * @param theType        type
     * @param theTitle       title
     * @param theDate        date
     * @param theDuration    duration (in seconds)
     * @param theDistance    distance (in meters)
     * @param theDescription description (optional, can be blank)
     */
    public Activity(ActivityType theType, String theTitle, Calendar theDate, int theDuration, double theDistance, String theDescription) {
        this.type = theType;
        this.title = theTitle;
        this.date = theDate;
        this.duration = theDuration;
        this.distance = theDistance;
        this.description = theDescription;
    }

    /**
     * Returns type.
     *
     * @return type
     */
    public ActivityType getType() {
        return this.type;
    }

    /**
     * Returns title.
     *
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns date.
     *
     * @return date
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Returns date as string.
     *
     * @return date as <i>dd/MM/yyyy</i>
     */
    public String getDateAsString() {
        return ACTIVITY_DATE_FORMAT.format(this.date.getTime());
    }

    /**
     * Returns duration.
     *
     * @return duration (in seconds)
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Returns distance.
     *
     * @return distance (in meters)
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Returns description (optional, can be blank).
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description (optional, can be left blank).
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", date=" + this.getDateAsString() +
                ", duration=" + duration +
                ", distance=" + distance +
                ", description='" + description + '\'' +
                '}';
    }
}
