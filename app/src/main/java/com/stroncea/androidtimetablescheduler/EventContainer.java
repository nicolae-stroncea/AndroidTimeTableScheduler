package com.stroncea.androidtimetablescheduler;
import java.util.List;

public interface  EventContainer<E extends Event<E>> {
//    public boolean hasAConflict();
    public void checkForConflict(List<EventGroup<E>> listOfEventGroups);
}
