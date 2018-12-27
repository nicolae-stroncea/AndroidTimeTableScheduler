package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is UofT specific because this is about lectures and tutorials
 * repeating at the same time.
 */
//TODO consider making it more general so it can apply to other cases where classes may repeat
// at the same time
public class UofTChooseFromEventGroups extends ChooseFromEventGroups<UofTEvent> {
    public UofTChooseFromEventGroups(String name) {
        this.name = name;
    }
    public UofTChooseFromEventGroups(){
        super();
    }

    /**
     * check if it UofTChoiceOfEventGroup is made of groups of 1 event
     * @return whether this object can be cleaned
     */
    public boolean checkIfCleanable(){
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
        if (checkIfCleanable()) {
            List<EventGroup<UofTEvent>> listOfEventGroups = getListOfOptions();
            List<EventGroup<UofTEvent>> newListOfEventGrups = new ArrayList<>();
            Set<UofTEvent> setOfEvents = new HashSet<>();
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
            UofTEventGroup eventGroup;
            List<UofTEvent> lonelyEventList;
            for(UofTEvent event: setOfEvents){
                lonelyEventList = new ArrayList<>();
                lonelyEventList.add(event);
                eventGroup = new UofTEventGroup();
                eventGroup.setEventGroup(lonelyEventList);
                eventGroup.setBundle(bundle);
                newListOfEventGrups.add(eventGroup);
            }
            setListOfOptions(newListOfEventGrups);
    }

}





}
