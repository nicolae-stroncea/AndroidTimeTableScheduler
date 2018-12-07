package com.stroncea.androidtimetablescheduler;
public class UofTCourse extends OptionsOfEventGroups<UofTEvent> {
    public UofTCourse(String name) {
        this.name = name;
    }
    public UofTCourse(){
        super();
    }
    @Override
    public boolean equals(Object obj) {
        UofTCourse alma = (UofTCourse) obj;
        return this.getName().equals(alma.getName());
    }





}
