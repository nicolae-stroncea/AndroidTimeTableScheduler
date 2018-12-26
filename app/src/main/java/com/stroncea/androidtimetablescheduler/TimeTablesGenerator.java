package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
Generates all possible TimeTables given a list  of choice of EventGroups.
 */
public abstract class TimeTablesGenerator<E extends Event<E>, T extends TimeTable<E,T>, C extends ChoiceOfEventGroups<E>> implements Comparator<T>, Serializable, TimeTableCreator<E,T> {
    private List<C> buildingBlocks;
    private List<T> timeTables = new ArrayList<>();
    private List<UserPreferences> userPref;

    /**
     * Gets passed a list of ChoiceOfEventGroups's, which are objects that contain alternatives for a course, i.e
     * Lec101, Lec205, Lec104 for a course CSC108.
     * @param buildingBlocks is a list of ChoiceOfEventGroups to be combined together.
     */
    public TimeTablesGenerator(List<C> buildingBlocks){
        this.buildingBlocks = buildingBlocks;
    }
    public TimeTablesGenerator(){
        this.buildingBlocks = new ArrayList<>();
    }
    public TimeTablesGenerator(List<C> buildingBlocks, List<UserPreferences> userPref){
        this.buildingBlocks = buildingBlocks;
        this.userPref= userPref;
    }
    /**
     * Create all possible TimeTable Combinations and sorts them according to score
     */
    //TODO transform this into normal forLoops. It's a lot faster that way.
    public void createTimeTables(){
        if(buildingBlocks.size()!=0){
            createHelper(0, new ArrayList<EventGroup<E>>());
            Collections.sort(timeTables, this);
        }
    }

    /**
     * Let's say we have: [CSC108[x1,x2],Mat235[y1,y2],CSC165[z1].
     * We would build timetable: [x1,y1,z1],[x1,y2,z1],[x2,y1,z1],[x2,y2,z1].
     * @param index within buildingBlocks which shows at which UnkownP we are currently at.
     * @param items is a List of ChoiceOfEventGroups's in this combination.
     */
    private void createHelper(int index, List<EventGroup<E>> items){
        // If we're at the last b1uildingBlock in the list
        if (index == buildingBlocks.size() - 1) {
            for(EventGroup<E> e :buildingBlocks.get(index)){
                items.add(e);
                // create a new arrayList with items
                // because otherwise the current one is modified
                if(!hasAConflict(new ArrayList<>(items))){
                    T t = createTimeTable(new ArrayList<>(items));
                    t.giveScore();
                    timeTables.add(t);

                }
                items.remove(items.size()-1);
            }
        }
        else{
            // Add all the events from 1
            for(EventGroup<E> e :buildingBlocks.get(index)){
                items.add(e);
                createHelper(index+1, items);
                items.remove(items.size()-1);
            }

        }
    }



    public void clearBuildingBlocks(){
        buildingBlocks.clear();
    }



    public List<C> getBuildingBlocks() {
        return buildingBlocks;
    }

    public void setBuildingBlocks(List<C> buildingBlocks) {
        this.buildingBlocks = buildingBlocks;
    }
    public void addBuildingBlocks(C newBuildingBlock){
        buildingBlocks.add(newBuildingBlock);
    }
    // The timmeTable with the smallest score is actually the biggest
    // Therefore, we need to reverse them.
    @Override
    public int compare(T o1, T o2){
        int comparingResult = o1.compareTo(o2);
        return -comparingResult;
    }
    public List<T> getTimeTables() {
        return timeTables;
    }

    /**
     * We have a list of a list of events. Within each list of events, we know that all the events do not have a conflict
     * with one another. Therefore we will test every event in 1 list of events against every event in another list of events
     * Identify if you have a conflict between events. Assumes all of them may have a conflict between EachOther
     * @param listOfEventGroups is a list of EventGroups, where all Events in an EventGroup are related to each other. listOfEventGroups is all the events that are part of this timeTable
     *
     * */
    public boolean hasAConflict(List<EventGroup<E>> listOfEventGroups) {
        List<E> allEvents = new ArrayList<>();
        for (EventGroup<E> eventList : listOfEventGroups) {
            allEvents.addAll(eventList.getEventGroup());
        }
        for (int i = 0; i < allEvents.size(); i++) {
            for (int j = i + 1; j < allEvents.size(); j++) {
                if (allEvents.get(i).intersects(allEvents.get(j))) {
                    return true;
                }
            }
        }
    return false;
    }


}
