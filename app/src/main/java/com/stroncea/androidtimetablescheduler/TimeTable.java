package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Valid List of EventGroups(From class description: all events in an eventGroup
 * must go together). There is no conflicts between any EventGroups.
 * Must implement Compare(), where it decides how to sort the timeTables by score.
 * It must implement it because with different events, you will sort timeTables differently.
 * For example with a weekly event, you will compare by day of week, monthly event, by day of month.
 *
 * Must implement Scorable, where you choose your custom filters on what attributes you want to score
 * a timeTable
 */

public abstract class TimeTable<E extends Event<E>, T extends TimeTable<E,T>> implements Comparable<T>, Scorable, Serializable {
    private List<EventGroup<E>> listOfEventGroups = new ArrayList<>();
    // by default score is -1
    private int score = -1;

    public TimeTable() {}

    /**
     *
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
     *
     * @param listOfEventGroups Set the different eventGroups to build the timeTable From
     */
    public void setEvents(List<EventGroup<E>> listOfEventGroups) {
        this.listOfEventGroups = listOfEventGroups;
    }

   /**
     * Get the groups
     * @return the listOfEventGroups
     */
     public List<EventGroup<E>> getListOfEventGroups() {
        return listOfEventGroups;
    }
     public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
