package com.stroncea.androidtimetablescheduler;

import lombok.Data;

@Data
public class UofTEvent extends WeeklyEvent<UofTEvent> {
    private String location;
    private String lectureSection;

}
