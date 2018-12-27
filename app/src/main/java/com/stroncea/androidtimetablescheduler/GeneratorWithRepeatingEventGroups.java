package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This represents a TimeTableGenerator which is optimized for dealing with RepeatingGroups.
 * A repeated group is one such that eventGroup1.isEqual(eventGroup2) returns true.
 * For example if 2 lectures have identical times and locations, with only differences being professsors
 * and places, they are counted as a repeated group
 *
 */
public class GeneratorWithRepeatingEventGroups<E extends Event<E>, T extends TimeTable<E,T>>  extends TimeTablesGenerator<E,T> {
    private Map<EventGroup<E>, List<EventGroup<E>>> bundle;

    public GeneratorWithRepeatingEventGroups(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t){
        super(buildingBlocks,t);
        bundle = new HashMap<>();
    }
    public GeneratorWithRepeatingEventGroups(TimeTableCreator<E,T> t){
        super(t);
        bundle = new HashMap<>();

    }
    public GeneratorWithRepeatingEventGroups(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t, List<UserPreferences> userPref){
        super(buildingBlocks, t,userPref);
        bundle = new HashMap<>();

    }

    public Map<EventGroup<E>, List<EventGroup<E>>> getBundle() {
        return bundle;
    }

    public void setBundle(Map<EventGroup<E>, List<EventGroup<E>>> bundle) {
        this.bundle = bundle;
    }
    public void addToBundle(EventGroup<E> evGroup, List<EventGroup<E>> listOfEv){
        bundle.put(evGroup, listOfEv);
    }
    public void addToBundle(Map< EventGroup<E>,List<EventGroup<E>>> bundleOfItems){
        if(bundleOfItems.size() != 0 ){
            for(Map.Entry<EventGroup<E>, List<EventGroup<E>>> e: bundleOfItems.entrySet()){
                EventGroup<E> evGroup = e.getKey();
                List<EventGroup<E>> listOfEv = e.getValue();
                bundle.put(evGroup, listOfEv);

            }
        }

    }
}
