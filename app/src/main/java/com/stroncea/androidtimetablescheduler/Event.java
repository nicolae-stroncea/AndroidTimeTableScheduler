package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;

/**
 * Represents a single event
 * @param <E> this MUST BE the same as the name of the subclass which extends it. For example
 * public TestEvent extends Event<TestEvent>.
 */
public abstract class Event<E extends Event<E>> implements Serializable {
    /**
     * WHen event starts
     */
    private int startTime;
    /**
     * When event ends
     */
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
    public Event(){ }

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

    /**
     * @param object represent the other Event object being checked against
     * @return whether the 2 events happen at the same time. This is used to detect conflicts
     * in a timetable.
     */
    public abstract boolean intersects(E object);

    /**
     * This is implemented because equals is implemented
     * @return the hashCode
     */
    @Override
    public abstract int hashCode();

    /**
     * Must define behaviour for when 2 events are equal. See WeeklyEvent as example.
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);
}
