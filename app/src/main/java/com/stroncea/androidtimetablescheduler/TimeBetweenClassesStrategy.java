package com.stroncea.androidtimetablescheduler;

import java.util.List;

public class TimeBetweenClassesStrategy<E extends Event<E>> implements SoftConstraintStrategy<E>{
    public static final SoftUserPreference pref = SoftUserPreference.TIME_BTN_CLASSES;
    @Override
    public int scoreForPreference(int valueOfPref, List<List<E>> events){
       int score = 0;
       // Calculate the difference between events in the same Day
       for(int i=0; i<events.size(); i++){
           List<E> eventsByDay =events.get(i);
           for(int j=1; j<eventsByDay.size();j++){
               E e = eventsByDay.get(j);
               // add the difference between the value and the time between events
               //TODO remember, i'm assuming here that i get the value in hours
               score += Math.abs((e.getStartTime()-eventsByDay.get(j-1).getEndTime()) - valueOfPref * 3600);
           }
       }
       return score;
   }

}

