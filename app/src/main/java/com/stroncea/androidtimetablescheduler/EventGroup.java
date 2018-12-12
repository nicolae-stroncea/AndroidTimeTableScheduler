package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of related events: For example it would represent a Lecture L0101, with 3 Events, each one representing an event on monday, wednesday, friday.
 * @param <E>
 */
public class EventGroup<E extends Event<E>> implements Serializable {
    private List<E> eventGroup;
    //TODO consider putting eventGroup in its own subclass of UoftEventGroup
    private String instructor;
    public EventGroup(List<E> eventGroup){
        this.eventGroup=eventGroup;
    }
    public EventGroup(){
        this.eventGroup=new ArrayList<>();
    }

    public List<E> getEventGroup() {
        return eventGroup;
    }
    public int size(){
        return eventGroup.size();
    }
    public E get(int i){
        return eventGroup.get(i);
    }

    public void setEventGroup(List<E> eventGroup) {
        this.eventGroup = eventGroup;
    }
    public void addEvent(E e){
        this.eventGroup.add(e);
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
