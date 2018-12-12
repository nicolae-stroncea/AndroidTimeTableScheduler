package com.stroncea.androidtimetablescheduler;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is UofT specific because this is about tutorials repeating at the same time.
 */
public class UofTChoiceOfEventGroups extends ChoiceOfEventGroups<UofTEvent> {
    // map which
    private Map<String, String> bundle = new HashMap<>();
    public UofTChoiceOfEventGroups(String name) {
        this.name = name;
    }
    public UofTChoiceOfEventGroups(){
        super();
    }
    @Override
    public boolean equals(Object obj) {
        UofTChoiceOfEventGroups alma = (UofTChoiceOfEventGroups) obj;
        return this.getName().equals(alma.getName());
    }

    /**
     * check if it UofTChoiceOfEventGroup is made of groups of 1 event
     * @return
     */
    public boolean checkIfEligible(){
        List<EventGroup<UofTEvent>> listOfEventGroups = getListOfOptions();
        for(EventGroup<UofTEvent> ev: listOfEventGroups){
            if(ev.size()!=1){
                return false;
            }
        }
        return true;

    }
    public void cleanOption() {
        if (checkIfEligible()) {
            List<EventGroup<UofTEvent>> listOfEventGroups = getListOfOptions();
            List<EventGroup<UofTEvent>> newListOfEventGrups = new ArrayList<>();
            Set<UofTEvent> setOfEvents = new HashSet<>();
            // Let only the distinct Tutorials remain
            for(EventGroup<UofTEvent> evGroup:listOfEventGroups){
                for(int i=0;i<evGroup.size();i++){
                    bundle.put(this.getName(), evGroup.getInstructor());
                    setOfEvents.add(evGroup.get(i));
                }
            }
            EventGroup<UofTEvent> eventGroup;
            List<UofTEvent> lonelyEventList;
            for(UofTEvent event: setOfEvents){
                lonelyEventList = new ArrayList<>();
                lonelyEventList.add(event);
                eventGroup = new EventGroup<>();
                eventGroup.setEventGroup(lonelyEventList);
                newListOfEventGrups.add(eventGroup);
            }
            setListOfOptions(newListOfEventGrups);
    }

}





}
