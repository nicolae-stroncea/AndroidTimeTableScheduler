package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Single responsibility: Represents a group of events
 * WHICH MUST BE PART OF THE SAME group. I.E If one of the events form a EventGroup is present
 * in a timeTable, all of them must be. They can'generator be apart from each other. By definition,
 * THERE CANNOT BE ANY CONFLICTS BETWEEN events in an EventGroup. All of them must be separate
 * from each other
 * Examples: Would represent a High School Math Class, where you have a Math Event on Monday,
 * Math Event on Wednesday, and Math Event on Friday. All of these are part of the same EventGroup
 * and must be part of the same timeTable.
 ** @param <E> represents the kind of event
 */
public abstract class EventGroup<E extends Event<E>> implements Serializable {
    /**
     * the group of events which must all be part of the same timeTable,
     * i.e chem class(Monday Event, Tuesday Event, Friday Event).
     */
    @Getter @Setter
    private List<E> eventGroup;
    public EventGroup(List<E> eventGroup){
        this.eventGroup=eventGroup;
    }
    public EventGroup(){
        this.eventGroup=new ArrayList<>();
    }
    public int size(){
        return eventGroup.size();
    }
    public E get(int i){
        return eventGroup.get(i);
    }

    public void addEvent(E e){
        this.eventGroup.add(e);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EventGroup))
            return false;
        // put both into sets and compare if the sets are equal, based on whether they have all
        // the same events inside
        EventGroup<E> other = (EventGroup<E>) obj;
        HashSet<E> setOther = new HashSet<>(other.getEventGroup());
        HashSet<E> thisSet = new HashSet<>(eventGroup);
        return setOther.equals(thisSet);
        }

    @Override
    public int hashCode() {
        int hashCode =0;
        for(E ev:eventGroup){
            hashCode+=ev.hashCode();
        }
        return hashCode;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(E event:eventGroup){
            sb.append((event.toString()));
        }
        return sb.toString();
    }

    /**
     * Must be implemented for all Properties for which a user might want to set as a preference
     * for a event Group. For example if you create an EventGroup for a university,
     * properties of eventgroup might be all things that its event will have in common like:
     * instructor.
     * @return
     * @param eventProperty
     */
    public abstract String getPropertyValue(String eventProperty);

}

