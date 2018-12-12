package com.stroncea.androidtimetablescheduler;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
Generates all possible TimeTables given a list  of choice of EventGroups.
 */
public abstract class TimeTablesGenerator<E extends Event<E>, T extends TimeTable<E,T>> implements Comparator<T>, Serializable {
    private List<ChoiceOfEventGroups<E>> buildingBlocks;
    private List<T> timeTables = new ArrayList<>();
    List<UserPreferences> userPref;

    /**
     * Gets passed a list of ChoiceOfEventGroups's, which are objects that contain alternatives for a course, i.e
     * Lec101, Lec205, Lec104 for a course CSC108.
     * @param buildingBlocks is a list of ChoiceOfEventGroups to be combined together.
     */
    public TimeTablesGenerator(List<ChoiceOfEventGroups<E>> buildingBlocks){
        this.buildingBlocks = buildingBlocks;
    }
    public TimeTablesGenerator(){
        this.buildingBlocks = new ArrayList<>();
    }
    public TimeTablesGenerator(List<ChoiceOfEventGroups<E>> buildingBlocks, List<UserPreferences> userPref){
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
                T t = createTimeTable(new ArrayList<>(items));
                t.build();
                if(t.isValid()){
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
    public abstract T createTimeTable(List<EventGroup<E>>allEvents);

    public void clearBuildingBlocks(){
        buildingBlocks.clear();
    }



    public List<ChoiceOfEventGroups<E>> getBuildingBlocks() {
        return buildingBlocks;
    }

    public void setBuildingBlocks(List<ChoiceOfEventGroups<E>> buildingBlocks) {
        this.buildingBlocks = buildingBlocks;
    }
    public void addBuildingBlocks(ChoiceOfEventGroups<E> newBuildingBlock){
        buildingBlocks.add(newBuildingBlock);
    }
    // By default
    @Override
    public int compare(T o1, T o2){
        return o1.compareTo(o2);
    }
    public List<T> getTimeTables() {
        return timeTables;
    }
}
