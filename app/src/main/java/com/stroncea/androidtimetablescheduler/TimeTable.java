package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a group of events. A TimeTable can either be Valid or Invalid
 */

public abstract class TimeTable<E extends Event<E>, T extends TimeTable<E,T>> implements EventContainer<E>, Comparable<T>, Scorable, Serializable {
    private List<EventGroup<E>> listOfEventGroups = new ArrayList<>();
    private boolean isValid;
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
     * Builds the timeTable.
     */
    public void build(){
        checkForConflict(listOfEventGroups);
        if(isValid){
            giveScore();
        }

    }

    /**
     *
     * @param listOfEventGroups Set the different eventGroups to build the timeTable From
     */
    public void setEvents(List<EventGroup<E>> listOfEventGroups) {
        this.listOfEventGroups = listOfEventGroups;
    }

    /**
     * TimeTable is valid if it doesn't have conflicts. Not related to Filters.
     * @return whether timeTable is valid
     */
    public boolean isValid(){
        return isValid;
    }

    /**
     * We have a list of a list of events. Within each list of events, we know that all the events do not have a conflict
     * with one another. Therefore we will test every event in 1 list of events against every event in another list of events
     * Identify if you have a conflict between events. Assumes all of them may have a conflict between EachOther
     * @param listOfEventGroups is a list of EventGroups, where all Events in an EventGroup are related to each other. listOfEventGroups is all the events that are part of this timeTable
     *
     * */
    public void checkForConflict(List<EventGroup<E>> listOfEventGroups) {
        List<E> allEvents = getListOfEvents();
        for (int i = 0; i < allEvents.size(); i++) {
            for (int j = i + 1; j < allEvents.size(); j++) {
                if (allEvents.get(i).intersects(allEvents.get(j))) {
                    isValid = false;
                    return;
                }
            }
        }
        isValid = true;
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
