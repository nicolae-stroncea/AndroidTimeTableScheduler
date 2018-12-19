package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;

public abstract class Event<T extends Event<T>> implements Serializable {
    private int startTime;
    private int endTime;
    private String name;

    public Event(String name, int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }
    public Event(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Event(){

    }


    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    abstract boolean intersects(T object);
}
