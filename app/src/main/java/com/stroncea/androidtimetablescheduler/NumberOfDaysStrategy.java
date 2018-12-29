package com.stroncea.androidtimetablescheduler;

import java.util.List;


//TODO make this a class for TimeTableOnly, get another one for WeeklyTimeTable
public class NumberOfDaysStrategy<E extends WeeklyEvent<E>> implements SoftConstraintStrategy<E>{
    public static final SoftUserPreference pref = SoftUserPreference.NUMBER_OF_DAYS;
    WeeklyTimeTable<E> timeTable;

    public NumberOfDaysStrategy(WeeklyTimeTable<E> t){
        this.timeTable=t;
    }


    @Override
    public int scoreForPreference(int valueOfPref, List<List<E>> events){
        // return the distance between the actual date and the desired date
        return Math.abs(timeTable.getDaysWithEvents() - valueOfPref);

    }



}
