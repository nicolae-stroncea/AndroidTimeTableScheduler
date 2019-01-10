package com.stroncea.androidtimetablescheduler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * This represents a TimeTableGenerator which is optimized for dealing with RepeatingGroups.
 * A repeated group is one such that eventGroup1.isEqual(eventGroup2) returns true.
 * For example if 2 lectures have identical times and locations, with only differences being professsors
 * and places, they are counted as a repeated group
 *
 */
public class GeneratorWithRepeatingEventGroups<E extends Event<E>, T extends TimeTable<E,T>>  extends TimeTablesGenerator<E,T> {
    @Getter @Setter
    private Map<EventGroup<E>, List<EventGroup<E>>> bundle;

    public GeneratorWithRepeatingEventGroups(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t){
        super(buildingBlocks,t);
        bundle = new HashMap<>();
    }
    public GeneratorWithRepeatingEventGroups(TimeTableCreator<E,T> t){
        super(t);
        LinkedHashMap<SoftUserPreference, Integer> test = new LinkedHashMap<>();
        test.put(SoftUserPreference.TIME_OF_DAY, 0);
        test.put(SoftUserPreference.TIME_BTN_CLASSES, 0);
        test.put(SoftUserPreference.NUMBER_OF_DAYS,0);
        setSoftOrderingConstraints(test);
        bundle = new HashMap<>();

    }
    public GeneratorWithRepeatingEventGroups(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E,T> t, LinkedHashMap<SoftUserPreference, Integer> userPref){
        super(buildingBlocks, t,userPref);
        bundle = new HashMap<>();

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
