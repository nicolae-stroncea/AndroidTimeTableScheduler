package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single event
 * @param <E> this MUST BE the same as the name of the subclass which extends it. For example
 * public TestEvent extends Event<TestEvent>.
 */
public abstract class Event<E extends Event<E>> implements Serializable {
    /**
     * WHen event starts
     */
    @Getter @Setter private int startTime;
    /**
     * When event ends
     */
    @Getter @Setter private int endTime;
    @Getter @Setter private String name;

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
    /**
     * @param object represent the other Event object being checked against
     * @return whether the 2 events happen at the same time. This is used to detect conflicts
     * in a timetable. By default use WeeklyEvent and its intersects method, if your events
     * are structured around week. If they're structured around other types of denominator
     * (day of month, year, etc) you'll have to subclass it
     */
    public abstract boolean intersects(E object);

    /**
     * This is implemented because isEqual is implemented
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(startTime);
        sb.append(endTime);
        return sb.toString();
    }
}
