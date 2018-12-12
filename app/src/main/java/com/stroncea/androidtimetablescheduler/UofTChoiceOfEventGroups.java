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
        Map<String, List<String>> bundle = new HashMap<>();
        if (checkIfEligible()) {
            List<EventGroup<UofTEvent>> listOfEventGroups = getListOfOptions();
            List<EventGroup<UofTEvent>> newListOfEventGrups = new ArrayList<>();
            Set<UofTEvent> setOfEvents = new HashSet<>();
            // Let only the distinct Tutorials remain
            StringBuilder key;
            for(EventGroup<UofTEvent> evGroup:listOfEventGroups){
                for(int i=0;i<evGroup.size();i++){
                    key = new StringBuilder(String.valueOf(evGroup.get(i).getWeekDay()));
                    key.append("start");
                    key.append(evGroup.get(i).getStartTime());
                    key.append("end");
                    key.append(evGroup.get(i).getEndTime());
                    List<String> listOfEventNames= bundle.get(key.toString());
                    if(listOfEventNames==null) {
                        listOfEventNames = new ArrayList<>();
                    }
                    listOfEventNames.add(evGroup.get(i).getLectureSection());
                    bundle.put(key.toString(), listOfEventNames);
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
                eventGroup.setBundle(bundle);
                newListOfEventGrups.add(eventGroup);
            }
            setListOfOptions(newListOfEventGrups);
    }

}





}
