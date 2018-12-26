package com.stroncea.androidtimetablescheduler;

import java.util.List;
import java.util.Map;

public class UofTEventGroup extends EventGroup<UofTEvent>{
    //TODO consider putting eventGroup in its own subclass of UoftEventGroup
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
