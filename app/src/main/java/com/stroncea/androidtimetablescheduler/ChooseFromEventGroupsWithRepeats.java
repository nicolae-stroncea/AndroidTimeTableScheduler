package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This represents a ChooseFromGroups which has EventGroups that occur at the same times. Therefore
 * We can optimize generation of timeTable by bundling them together.
 */
public class ChooseFromEventGroupsWithRepeats<E extends Event<E>> extends ChooseFromEventGroups<E> {
    public ChooseFromEventGroupsWithRepeats(String name) {
        super(name);
    }
    public ChooseFromEventGroupsWithRepeats(){
        super();
    }


    /**
     * bundle together options that happen at the same time.
     */
    //TODO finish the method
    public Map<EventGroup<E>, List<EventGroup<E>>> stripRepeats() {
        List<EventGroup<E>> listOfEventGroups = getListOfOptions();
        Map<EventGroup<E>, List<EventGroup<E>>> bundle = new HashMap<>();

        for(EventGroup<E> ev: listOfEventGroups){
            List<EventGroup<E>> bundleList;
            if(bundle.containsKey(ev)){
                bundleList= bundle.get(ev);
            }
            else{
                bundleList = new ArrayList<>();
            }
            bundleList.add(ev);
            bundle.put(ev,bundleList);
        }
        Set<EventGroup<E>> uniqueEventGroups = bundle.keySet();
        setListOfOptions(new ArrayList<>(uniqueEventGroups));

        List<EventGroup<E>> toRemove = new ArrayList<>();
        // iterate through and remove the unique elements
        for(Entry<EventGroup<E>, List<EventGroup<E>>> e: bundle.entrySet()){
            EventGroup<E> evGroup = e.getKey();
            List<EventGroup<E>> listOfEv = e.getValue();
            if(listOfEv.size()<=1){
                toRemove.add(evGroup);
            }
        }
        for(EventGroup<E> eventGroup: toRemove){
            bundle.remove(eventGroup);
        }


        return bundle;



    }

    public Map<String, List<EventGroup<E>>>  stripRepeatsTest() {
        List<EventGroup<E>> listOfEventGroups = getListOfOptions();
        Map<String, List<EventGroup<E>>> bundle = new HashMap<>();
        return bundle;
    }
}
