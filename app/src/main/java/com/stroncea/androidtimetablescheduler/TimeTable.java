package com.stroncea.androidtimetablescheduler;
import com.stroncea.androidtimetablescheduler.SoftStrategies.SoftConstraintStrategy;
import com.stroncea.androidtimetablescheduler.SoftStrategies.SoftConstraintStrategyFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Valid List of EventGroups(From class description: all events in an eventGroup
 * must go together). There is no conflicts between any EventGroups.
 * Must have a score.
 * Must implement Compare(), because timetables are compared to each other.*
 * Must implement Scorable, where you choose your custom filters on what attributes you want to scoreForPreference
 * a timeTable
 */

public abstract class TimeTable<E extends Event<E>, T extends TimeTable<E,T>> implements Comparable<T>, Scorable<E>, Serializable {
    @Getter @Setter
    private List<EventGroup<E>> listOfEventGroups = new ArrayList<>();
    @Getter @Setter
    private int[] score;

    public TimeTable() {}

    /**
     *git l
     * @param listOfEventGroups represents a list of different EventGroups:
     * LEC101 of CSC258, LEC102 of CSC207, LEC201 of MAT235
     *
     */
    public TimeTable(List<EventGroup<E>> listOfEventGroups) {
        this.listOfEventGroups =listOfEventGroups;
    }

    /**
     *
     * @return the entire flattened list of events which represent this timeTable
     */

    public List<E> getListOfEvents(){
        List<E> listOfEvents = new ArrayList<>();
        for (EventGroup<E> eventList : listOfEventGroups) {
            listOfEvents.addAll(eventList.getEventGroup());
        }
        return listOfEvents;
    }


    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @param o
     * @return
     */
    @Override
    public int compareTo(T o) {
        int[] thisScores = this.getScore();
        int[] otherScores = o.getScore();
        for (int i = 0; i < thisScores.length; i++) {
            int thisScore = thisScores[i];
            int otherScore = otherScores[i];
            if (thisScore > otherScore) {
                return 1;
            } else if (thisScore < otherScore) {
                return -1;
            }
        }
        // if we've reached this point,
        // this must mean that all the scores are virtually identical
        return 0;
    }
    /**
     * Gives a scoreForPreference to each preference based on ensuring they're in the same order
     * THE BEST SCORE WILL BE THE LOWEST SCORE
     * @return the scoreForPreference
     */
    @Override
    public void giveScore(LinkedHashMap<SoftUserPreference, Integer> mapOfUserPreferences, SoftConstraintStrategyFactory<E> factory) {
        int counter = 0;
        int arraySize = mapOfUserPreferences.keySet().size();
        int[] thisScore = new int[arraySize];
        List<List<E>> lstOflstOfEvents = getListOfDayEvents();
        List<SoftConstraintStrategy<E>> strategyList =  new ArrayList<>();
        Set<SoftUserPreference> userPreferences = mapOfUserPreferences.keySet();
        Map<SoftUserPreference, SoftConstraintStrategy<E>> prefToStrategy = new HashMap<>();
        for(SoftUserPreference up: userPreferences){
            SoftConstraintStrategy<E> strategy = factory.getUserPreferenceBehaviours(up);
            strategyList.add(strategy);
            prefToStrategy.put(up,strategy);
        }
        for(Map.Entry<SoftUserPreference, Integer> entry : mapOfUserPreferences.entrySet()){
//            SoftConstraintStrategy<E> upb = hardConstraintFactory.getUserPreferenceBehaviours(entry.getKey());
//            thisScore[counter] = upb.scoreForPreference(entry.getValue(), lstOflstOfEvents);
            thisScore[counter] = prefToStrategy.get(entry.getKey()).scoreForPreference(entry.getValue(), lstOflstOfEvents);
            counter+=1;
        }
        setScore(thisScore);
    }


    /** One list of events is a day.
     * It is used to score the events according to user preferences, which is why every single
     * timetable child must override it it it hasn'generator been yet.
     * @return the list of days.
     */
    public abstract List<List<E>> getListOfDayEvents();


}
