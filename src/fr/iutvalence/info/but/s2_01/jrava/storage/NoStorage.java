package fr.iutvalence.info.but.s2_01.jrava.storage;

import fr.iutvalence.info.but.s2_01.jrava.model.Activity;
import fr.iutvalence.info.but.s2_01.jrava.model.Storage;

import java.util.HashMap;
import java.util.Map;


/**
 * Dummy implementation of storage service, nothing is stored/retrieved.
 *
 * @author Sebastien Jean
 * @version 1.0
 */
public class NoStorage implements Storage {
    /**
     * Next activity id.
     */
    private int nextId;

    /**
     * Creates a new <i>no storage</i> service.
     */
    public NoStorage() {
        this.nextId = 0;
    }

    @Override
    public int getNextId() {
        return this.nextId;
    }

    @Override
    public Map<Integer, Activity> loadActivities() {
        return new HashMap<>();
    }

    @Override
    public int saveNewActivity(Activity activity) {
        return this.nextId++;
    }

    @Override
    public void updateActivity(int activityId, Activity activity) {
    }

}
