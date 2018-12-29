package com.stroncea.androidtimetablescheduler;

import java.util.List;


public class NumberOfDaysStrategy<E extends Event<E>> implements SoftConstraintStrategy<E>{
    public static final SoftUserPreference pref = SoftUserPreference.NUMBER_OF_DAYS;

    @Override
    public int scoreForPreference(int valueOfPref, List<List<E>> events){
        // return the distance between the actual date and the desired date

        // find the number of days which have courses
        int daysWithEvents = 0;
        for(List<E> e: events){
            if(!e.isEmpty()){
                daysWithEvents+=1;
            }
        }

        return Math.abs(daysWithEvents - valueOfPref);

    }



}
