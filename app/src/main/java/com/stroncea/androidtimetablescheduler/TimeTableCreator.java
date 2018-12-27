package com.stroncea.androidtimetablescheduler;

import java.io.Serializable;
import java.util.List;

public interface TimeTableCreator<E extends Event<E>, T extends TimeTable<E,T>> extends Serializable {
    /**
     * Choose the type of timeTable to create
     * @param allEvents
     * @return the TimeTable
     */
    T createTimeTable(List<EventGroup<E>> allEvents);
}
