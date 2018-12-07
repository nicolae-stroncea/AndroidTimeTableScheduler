package com.stroncea.androidtimetablescheduler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
Generates all possible TimeTables
 */
public abstract class TimeTablesGenerator<E extends Event<E>, T extends TimeTable<E,T>> implements Comparator<T> {
    private List<OptionsOfEventGroups<E>> buildingBlocks;
    List<UserPreferences> userPref;

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }


    private List<TimeTable> timeTables = new ArrayList<>();

    /**
     * Gets passed a list of OptionsOfEventGroups's, which are objects that contain alternatives for a course, i.e
     * Lec101, Lec205, Lec104 for a course CSC108.
     * @param buildingBlocks is a list of OptionsOfEventGroups to be combined together.
     */
    public TimeTablesGenerator(List<OptionsOfEventGroups<E>> buildingBlocks){
        this.buildingBlocks = buildingBlocks;
    }
    public TimeTablesGenerator(){
        this.buildingBlocks = new ArrayList<>();
    }
    public TimeTablesGenerator(List<OptionsOfEventGroups<E>> buildingBlocks, List<UserPreferences> userPref){
        this.buildingBlocks = buildingBlocks;
        this.userPref= userPref;
    }
    /**
     * Create all possible TimeTable Combinations and sorts them according to score
     */
    public void createTimeTables(){
        List<List<E>> allEvents = new ArrayList<>();
        createHelper(0, allEvents);
    }

    /**
     * Let's say we have: [CSC108[x1,x2],Mat235[y1,y2],CSC165[z1].
     * We would build timetable: [x1,y1,z1],[x1,y2,z1],[x2,y1,z1],[x2,y2,z1].
     * @param index within buildingBlocks which shows at which UnkownP we are currently at.
     * @param items is a List of OptionsOfEventGroups's in this combination.
     */
    private void createHelper(int index, List<List<E>> items){
        // If we're at the last b1uildingBlock in the list
        if (index == buildingBlocks.size() - 1) {
            for(ArrayList<E> e :buildingBlocks.get(index)){
                List<List<E>> allEvents = new ArrayList<>();
                allEvents.addAll(items);
                allEvents.add(e);
                //TODO decide how to make it here better
                TimeTable t = createTimeTable(allEvents);
                t.build();
                if(t.isValid()){
                    timeTables.add(t);
                }

            }
        }
        else{
            // Add all the events from 1
            for(ArrayList<E> e :buildingBlocks.get(index)){
                List<List<E>> allEvents = new ArrayList<>();
                allEvents.addAll(items);
                allEvents.add(e);
                createHelper(index+1, allEvents);
            }

        }
    }
    public abstract TimeTable createTimeTable(List<List<E>> allEvents);



    public List<OptionsOfEventGroups<E>> getBuildingBlocks() {
        return buildingBlocks;
    }

    public void setBuildingBlocks(List<OptionsOfEventGroups<E>> buildingBlocks) {
        this.buildingBlocks = buildingBlocks;
    }
    public void addBuildingBlocks(OptionsOfEventGroups<E> newBuildingBlock){
        buildingBlocks.add(newBuildingBlock);
    }
    // By default
    @Override
    public int compare(T o1, T o2){
        return o1.compareTo(o2);
    }

}
