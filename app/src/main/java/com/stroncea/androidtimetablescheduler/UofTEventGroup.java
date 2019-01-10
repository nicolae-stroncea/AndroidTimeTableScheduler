package com.stroncea.androidtimetablescheduler;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a UofTEventGroup.
 */
public class UofTEventGroup extends EventGroup<UofTEvent>{
    @Getter @Setter
    private String instructor;
    @Getter @Setter
    private String subjectAndLecture;

    @Override
    public String getPropertyValue(String eventProperty) {
        switch (eventProperty) {
            case ("instructor"):
                return instructor;
            case("subjectAndLecture"):
                return subjectAndLecture;
            default:
                return null;

        }
    }
}
