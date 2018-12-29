package com.stroncea.androidtimetablescheduler;

import java.util.List;


//TODO make this a class for TimeTableOnly, get another one for WeeklyTimeTable
public class WeeklyUserPrefStrategies<E extends WeeklyEvent<E>> extends UserPrefStrategies<E, WeeklyTimeTable<E>> {
    WeeklyTimeTable<E> timeTable;

    public WeeklyUserPrefStrategies(WeeklyTimeTable<E> t){
        this.timeTable=t;
    }

    /**
     *
     * @param pref is the enum which represents a user's soft preference(soft meaning it
     * CAN be fulfilled but DOESN't HAVE TO
     * @param valueOfPref represents the value the user assigns to that preference
     * @param events sent here so it's not calculated thousands of times every time the method
     * gets called
     * @return
     */
    @Override
    public int scoreForPreference(SoftUserPreference pref, int valueOfPref, List<List<E>> events){
        if(pref.equals(SoftUserPreference.NUMBER_OF_DAYS)){
            // return the distance between the actual date and the desired date
            return Math.abs(timeTable.getDaysWithEvents() - valueOfPref);
        }
        else{
            return super.scoreForPreference(pref, valueOfPref, events);
        }
    }



}
