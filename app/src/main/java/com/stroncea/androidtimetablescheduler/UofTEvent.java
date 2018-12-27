package com.stroncea.androidtimetablescheduler;
public class UofTEvent extends WeeklyEvent<UofTEvent> {
    private String location;
    private String lectureSection;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLectureSection() {
        return lectureSection;
    }

    public void setLectureSection(String lectureSection) {
        this.lectureSection = lectureSection;
    }


}
