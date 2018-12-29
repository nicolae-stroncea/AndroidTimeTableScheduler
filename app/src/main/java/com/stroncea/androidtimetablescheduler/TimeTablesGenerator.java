package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
Generates all possible TimeTables given a list  of choice of EventGroups.
 */
public class TimeTablesGenerator<E extends Event<E>, T extends TimeTable<E,T>> implements  Serializable{
    private List<ChooseFromEventGroups<E>> buildingBlocks;
    private List<T> timeTables = new ArrayList<>();
    /**
     * is a map of the userPreferences, the order in which they count is the order of priority
 *      i.e SoftUserPreference at index 0 is more important than SoftUserPreference at index 1, which is more
 *      important than at index 2 and so on.
 *      the value of the map is the value they assign to it. For example if they set enum TIME_BTN_CLASSES
     *  be 4, that means they want 4 30-min blocks of free time btn classes
     */
    //TODO refactor the map so that when you shove 1hr blocks, it doubles it to account for fact that
    //we're adding half hour slots rather than 1 hour slots


    private LinkedHashMap<SoftUserPreference, Integer> userPref;
    private TimeTableCreator<E,T> timeTableCreator;

    /**
     * Gets passed a list of ChooseFromEventGroups's, which are objects that contain alternatives for a course, i.e
     * Lec101, Lec205, Lec104 for a course CSC108.
     * @param buildingBlocks is a list of ChooseFromEventGroups to be combined together.
     */
    public TimeTablesGenerator(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t){
        this.timeTableCreator = t;
        this.buildingBlocks = buildingBlocks;
    }
    public TimeTablesGenerator(TimeTableCreator<E,T> t){
        this.timeTableCreator = t;
        this.buildingBlocks = new ArrayList<>();
    }
    public TimeTablesGenerator(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t, LinkedHashMap<SoftUserPreference, Integer> userPref){
        this(buildingBlocks,t);
        this.userPref= userPref;
    }
    public TimeTablesGenerator(){

    }
    /**
     * Create all possible TimeTable Combinations and sorts them according to scoreForPreference
     */
    //TODO transform this into normal forLoops. It's a lot faster that way.
    public void createTimeTables(){
        if(buildingBlocks.size()!=0){
            createHelper(0, new ArrayList<EventGroup<E>>());
            Collections.sort(timeTables);
        }
    }

    /**
     * Let's say we have: [CSC108[x1,x2],Mat235[y1,y2],CSC165[z1].
     * We would build timetable: [x1,y1,z1],[x1,y2,z1],[x2,y1,z1],[x2,y2,z1].
     * @param index within buildingBlocks which shows at which UnkownP we are currently at.
     * @param items is a List of ChooseFromEventGroups's in this combination.
     */
    private void createHelper(int index, List<EventGroup<E>> items){
        // If we're at the last b1uildingBlock in the list
        if (index == buildingBlocks.size() - 1) {
            for(EventGroup<E> e :buildingBlocks.get(index)){
                items.add(e);
                // create a new arrayList with items
                // because otherwise the current one is modified
                if(!hasAConflict(new ArrayList<>(items))){
                    T t = timeTableCreator.createTimeTable(new ArrayList<>(items));
                    t.giveScore(userPref);
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



    public List<ChooseFromEventGroups<E>> getBuildingBlocks() {
        return buildingBlocks;
    }

    public void setBuildingBlocks(List<ChooseFromEventGroups<E>> buildingBlocks) {
        this.buildingBlocks = buildingBlocks;
    }
    public void addBuildingBlocks(ChooseFromEventGroups<E> newBuildingBlock){
        buildingBlocks.add(newBuildingBlock);
    }

    /**
     *
     * @return all timeTables
     */
    public List<T> getTimeTables() {
        return timeTables;
    }

    /**
     * We have a list of a EventGroups. Within each EventGroup, we know that all the events do not have a conflict
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

    public LinkedHashMap<SoftUserPreference, Integer> getUserPref() {
        return userPref;
    }

    public void setUserPref(LinkedHashMap<SoftUserPreference, Integer> userPref) {
        this.userPref = userPref;
    }
}
