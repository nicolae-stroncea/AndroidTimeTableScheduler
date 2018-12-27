package com.stroncea.androidtimetablescheduler;
import java.util.List;

public class WeeklyTimeTableCreator<E extends WeeklyEvent<E>> implements TimeTableCreator<E, WeeklyTimeTable<E>>{
    @Override
    public WeeklyTimeTable<E> createTimeTable(List<EventGroup<E>> allEvents){
        return new WeeklyTimeTable<>(allEvents);
    }


}
