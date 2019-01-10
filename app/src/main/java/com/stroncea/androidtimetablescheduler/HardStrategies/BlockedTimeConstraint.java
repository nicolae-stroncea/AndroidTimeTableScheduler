package com.stroncea.androidtimetablescheduler.HardStrategies;

import com.stroncea.androidtimetablescheduler.EventGroup;
import com.stroncea.androidtimetablescheduler.Event;

import java.util.List;
public class BlockedTimeConstraint<E extends Event<E>> implements HardConstraintChecker<E>{


    @Override
    public boolean hardConstraintSatisfied(List<String> blockedValuesAsStrings, EventGroup<E> evGroup) {
        int numConstraints = 0;
        List<E> blockedValues = convertStringsToEvents(blockedValuesAsStrings);
        // need to convert the string back to a valid starTime and EndTime
        for(E blockedEvent : blockedValues){

            for(E e: evGroup.getEventGroup()){
                if(e.intersects(blockedEvent)){
                    // if any event in the eventGroup intersects with at least 1
                    return false;
                }
            }

        }
        return true;
    }
    public List<E> convertStringsToEvents(List<String> toConvert){
        for(String value:toConvert){
            int dayOfWeek = Character.getNumericValue(value.charAt(0));
            // remove first character
            value = value.substring(1);
            int startTime = Integer.parseInt(value.substring(0, value.length()/2));
            int endTime = Integer.parseInt(value.substring(value.length()/2));

        }
        return null;
    }
}
