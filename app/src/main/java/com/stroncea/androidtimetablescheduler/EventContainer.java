package com.stroncea.androidtimetablescheduler;
import java.util.List;

public interface  EventContainer<E> {
//    public boolean checkForConflict();
    public void checkForConflict(List<List<E>> listOfEventGroups);
}
