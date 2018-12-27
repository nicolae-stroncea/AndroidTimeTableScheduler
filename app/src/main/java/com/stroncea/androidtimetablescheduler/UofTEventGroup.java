package com.stroncea.androidtimetablescheduler;

import java.util.List;
import java.util.Map;

/**
 * Represents a UofTEventGroup.
 */
public class UofTEventGroup extends EventGroup<UofTEvent>{
    private String instructor;
    private Map<String, List<String>> eventGroupBundle;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public Map<String, List<String>> getBundle() {
        return eventGroupBundle;
    }

    public void setBundle(Map<String, List<String>> bundle) {
        this.eventGroupBundle = bundle;
    }




}
