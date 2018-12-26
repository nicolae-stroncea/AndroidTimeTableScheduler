package com.stroncea.androidtimetablescheduler;

import java.util.List;

public interface TimeTableCreator<E extends Event<E>, T extends TimeTable<E,T>>{
    /**
     * Choose the type of timeTable to create
     * @param allEvents
     * @return the TimeTable
     */
    T createTimeTable(List<EventGroup<E>> allEvents);
}
