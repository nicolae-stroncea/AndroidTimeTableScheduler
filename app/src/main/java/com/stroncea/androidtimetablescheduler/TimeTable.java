package com.stroncea.androidtimetablescheduler;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block of events.
 */

public abstract class TimeTable<E extends Event, T extends TimeTable> implements EventContainer<E>, Comparable<T>, Scorable {
    /*
        Generates a timetable if possible. If not, returns null.
         */
    private List<List<E>> listOfEventGroups = new ArrayList<>();
    private boolean isValid;

    // by default score is -1
    private int score = -1;
    public TimeTable()
    {}
    public TimeTable(List<List<E>> listOfEventGroups) {
        this.listOfEventGroups =listOfEventGroups;
    }

    /**
     * Builds the timeTable and Returns it
     * @return the timetable just built
     */
    public void build(){
        checkForConflict(listOfEventGroups);
        if(isValid){
            giveScore();
        }

    }

    public void setEvents(List<List<E>> listOfEventGroups) {
        this.listOfEventGroups = listOfEventGroups;
    }
    public boolean isValid(){
        return isValid;
    }

    /**
     * We have a list of a list of events. Within each list of events, we know that all the events do not have a conflict
     * with one another. Therefore we will test every event in 1 list of events against every event in another list of events
     * Identify if you have a conflict between events. Assumes all of them may have a conflict between EachOther
     * @param listOfEventGroups
     * @return
     * */
    public void checkForConflict(List<List<E>> listOfEventGroups) {
        int index;
        // we will need to first get an eventGroup.
        for(int i=0;i<listOfEventGroups.size();i++){
            // now we take each element from the eventGroup, and try to compare it against each element
            // from every other eventGroup.
            List<E> eventGroup= listOfEventGroups.get(i);
            // we first get the current element in our current EventGroup
            for(int j=0;j<eventGroup.size();j++){
                E event = eventGroup.get(j);
                index = i+1;
                // Iterate through every next eventGroup
                while(index<listOfEventGroups.size()){
                    List<E> eventGrouptoCompare= listOfEventGroups.get(index);
                    // compare against every element within the eventGroupToCompare
                    for(int k=0;k<eventGrouptoCompare.size();k++){
                        if(event.intersects(eventGrouptoCompare.get(k))){
                             isValid= false;
                             return;
                        }
                    }
                    index++;
                }
            }
        }
        isValid = true;
    }
    //TODO See where to get rid of generics in latest changes, and maybe overall. Only implement where necessary.
    // maybe just keep everything about scoring from last commit, discard all other changes

     public List<List<E>> getListOfEventGroups() {
        return listOfEventGroups;
    }
     public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
