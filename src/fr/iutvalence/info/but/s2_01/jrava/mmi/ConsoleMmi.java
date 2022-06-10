package fr.iutvalence.info.but.s2_01.jrava.mmi;

import fr.iutvalence.info.but.s2_01.jrava.model.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

/**
 * Console implementation of mmi service.
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class ConsoleMmi implements Mmi {

    /**
     * Scanner used for command input.
     */
    private final Scanner in;

    /**
     * Activity tracker.
     */
    private ActivityTracker tracker;

    /**
     * Create a new console mmi, for a given activity tracker.
     *
     * @param activityTracker activity tracker
     */
    public ConsoleMmi(ActivityTracker activityTracker) {
        this.setActivityTracker(activityTracker);
        this.in = new Scanner(System.in);
    }

    @Override
    public void setActivityTracker(ActivityTracker activityTracker) {
        this.tracker = activityTracker;
    }

    @Override
    public void displayMMI() {
        while (true) {
            this.displayMainMenu();
            String command = this.in.nextLine();
            switch (command) {
                case "N":
                    this.createActivity();
                    break;
                case "L":
                    this.displayActivitiesMenu();
                    break;
                case "S":
                    this.displayStatsMenu();
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Unsupported command!");
            }
        }

    }

    /**
     * Displays main menu (create activity, view activities/stats).
     */
    private void displayMainMenu() {
        System.out.println("---------- ");
        System.out.println("Main menu: ");
        System.out.println("---------- ");
        System.out.println("create an activity -> N");
        System.out.println("view activities -> L");
        System.out.println("view stats -> S");
        System.out.println("exit -> X");
        System.out.println("---------- ");
    }

    /**
     * Displays activities menu (by type, by id, stats).
     */
    private void displayActivitiesMenu() {
        this.displayActivities();
        while (true) {
            this.displayActivitiesSubMenu();
            String command = this.in.nextLine();
            switch (command.split("-")[0]) {
                case "T":
                    this.displayActivities(ActivityType.valueOf(command.split("-")[1]));
                    break;
                case "I":
                    this.displayActivityMenu(Integer.parseInt(command.split("-")[1]));
                    break;
                case "S":
                    this.displayStatsMenu();
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Unsupported command!");
            }
        }
    }

    /**
     * Displays activities sub-menu (filter by type, select by id).
     */
    private void displayActivitiesSubMenu() {
        System.out.println("---------------- ");
        System.out.println("Activities menu: ");
        System.out.println("---------------- ");
        System.out.println();
        System.out.println("filter by type -> T-[SWIM|RUN|...]");
        System.out.println("select by ID -> I-[id]");
        System.out.println("back to main menu -> X");
        System.out.println("---------------- ");
    }

    /**
     * Displays activity menu (brief, update).
     *
     * @param activityId activity id
     */
    private void displayActivityMenu(int activityId) {
        this.displayActivity(activityId, this.tracker.getActivityById(activityId));
        while (true) {
            this.displayActivitySubMenu();
            String command = this.in.nextLine();
            switch (command.split("-")[0]) {
                case "T":
                    try {
                        this.tracker.updateActivityTitle(activityId, command.split("-")[1]);
                    } catch (StorageAccessException e) {
                        System.out.println("[Warning] storage access failed, activity not saved");
                    }
                    break;
                case "D":
                    try {
                        this.tracker.updateActivityDescription(activityId, command.split("-")[1]);
                    } catch (StorageAccessException e) {
                        System.out.println("[Warning] storage access failed, activity not saved");
                    }
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Unsupported command!");
            }
        }
    }

    /**
     * Displays activity sub-menu (update title/description).
     */
    private void displayActivitySubMenu() {
        System.out.println("-------------- ");
        System.out.println("Activity menu: ");
        System.out.println("-------------- ");
        System.out.println();
        System.out.println("update title -> T-[...]");
        System.out.println("update description -> D-[...]");
        System.out.println("back to activities menu -> X");
        System.out.println("-------------- ");
    }

    /**
     * Displays stats menu.
     */
    private void displayStatsMenu() {
        this.displayStats();
        while (true) {
            this.displayStatsSubMenu();
            String command = this.in.nextLine();
            switch (command.split("-")[0]) {
                case "T":
                    this.displayStats(ActivityType.valueOf(command.split("-")[1]));
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Unsupported command!");
            }
        }
    }

    /**
     * Displays stats sub-menu (filter by type).
     */
    private void displayStatsSubMenu() {
        System.out.println("----------- ");
        System.out.println("Stats menu: ");
        System.out.println("----------- ");
        System.out.println();
        System.out.println("filter by type -> T-[SWIM|RUN|...]");
        System.out.println("back to main menu -> X");
        System.out.println("----------- ");
    }

    /**
     * Displays stats, whatever activity type.
     */
    private void displayStats() {
        System.out.println("Statistics:");
        this.displayStats(this.tracker.getStats());
    }

    /**
     * Displays stats, for a given activity type.
     *
     * @param activityType activity type
     */
    private void displayStats(ActivityType activityType) {
        System.out.println("Statistics (" + activityType + " only):");
        this.displayStats(this.tracker.getStats(activityType));
    }

    /**
     * Displays given activity stats.
     *
     * @param stats activity stats
     */
    private void displayStats(ActivityStats stats) {
        System.out.println(stats.getGlobalCount() + " total activities");
        System.out.println("This year : ");
        System.out.println("\t" + stats.getYearlyCount() + " activities");
        System.out.println("\t" + stats.getYearlyDuration() + " s");
        System.out.println("\t" + stats.getYearlyDistance() + " m");
        System.out.println("This month : ");
        System.out.println("\t" + stats.getMonthlyCount() + " activities");
        System.out.println("\t" + stats.getMonthlyDuration() + " s");
        System.out.println("\t" + stats.getMonthlyDistance() + " m");
    }

    /**
     * Displays all activities.
     */
    private void displayActivities() {
        System.out.println("List of activities:");
        this.displayActivities(this.tracker.getActivities());
    }

    /**
     * Displays activities, for a given activity type.
     *
     * @param activityType activity type
     */
    private void displayActivities(ActivityType activityType) {
        System.out.println("List of activities (" + activityType + " only):");
        this.displayActivities(this.tracker.getActivitiesByType(activityType));
    }

    /**
     * Displays activities.
     *
     * @param activities activities
     */
    private void displayActivities(Map<Integer, Activity> activities) {
        for (Map.Entry<Integer, Activity> entry : activities.entrySet())
            this.displayActivityBrief(entry.getKey(), entry.getValue());
        System.out.println(activities.size() + " activities");
    }

    /**
     * Creates a new activity.
     */
    private void createActivity() {
        ActivityType type = null;
        System.out.println("type?");
        try {
            type = ActivityType.valueOf(this.in.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("[FAILED] activity type is invalid");
            return;
        }

        System.out.println("title?");
        String title = this.in.nextLine();

        System.out.println("date (as dd/mm/yyyy)?");
        String dateString = this.in.nextLine();
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(Activity.ACTIVITY_DATE_FORMAT.parse(dateString));
        } catch (ParseException e) {
            System.out.println("[FAILED] date is invalid");
            return;
        }

        System.out.println("duration (in seconds)?");
        int duration = 0;
        String durationString = this.in.nextLine();
        try {
            duration = Integer.parseInt(durationString);
        } catch (NumberFormatException e) {
            System.out.println("[FAILED] duration is invalid");
            return;
        }

        System.out.println("distance (in meters)?");
        double distance = 0;
        String distanceString = this.in.nextLine();
        try {
            distance = Double.parseDouble(distanceString);
        } catch (NumberFormatException e) {
            System.out.println("[FAILED] distance is invalid");
            return;
        }

        System.out.println("description (can be left empty)?");
        String description = this.in.nextLine();

        Activity activity = new Activity(type, title, date, duration, distance, description);
        this.tracker.addActivity(activity);
        System.out.println("activity successfully added");
    }

    /**
     * Displays activity brief.
     *
     * @param activityId activity id
     * @param activity   activity
     */
    private void displayActivityBrief(int activityId, Activity activity) {
        String activityBrief = "[" + "id: " + activityId
                + ", date: " + activity.getDateAsString()
                + ", type: " + activity.getType()
                + ", title: " + activity.getTitle()
                + ", duration: " + activity.getDuration() + " s"
                + ", distance: " + activity.getDistance() + " m";
        System.out.println(activityBrief);
    }

    /**
     * Displays activity detail.
     *
     * @param activityId activity id
     * @param activity   activity
     */
    private void displayActivity(int activityId, Activity activity) {
        System.out.println("id: " + activityId);
        System.out.println("type: " + activity.getType());
        System.out.println("title: " + activity.getTitle());
        System.out.println("date: " + activity.getDateAsString());
        System.out.println("duration: " + activity.getDuration() + " s");
        System.out.println("distance: " + activity.getDistance() + " m");
        System.out.println("description: " + activity.getDescription() + " m");
    }
}
