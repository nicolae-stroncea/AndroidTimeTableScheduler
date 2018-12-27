package com.stroncea.androidtimetablescheduler;
import java.util.List;

public class WeeklyTimeTablesGenerator<E extends WeeklyEvent<E>> extends TimeTablesGenerator<E, WeeklyTimeTable<E>>{
    @Override
    public WeeklyTimeTable<E> createTimeTable(List<EventGroup<E>> allEvents){
        return new WeeklyTimeTable<>(allEvents);
    }


}
