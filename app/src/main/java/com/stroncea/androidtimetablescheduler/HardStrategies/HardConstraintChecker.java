package com.stroncea.androidtimetablescheduler.HardStrategies;

import com.stroncea.androidtimetablescheduler.Event;
import com.stroncea.androidtimetablescheduler.EventGroup;

import java.io.Serializable;
import java.util.List;

public interface HardConstraintChecker<E extends Event<E>> extends Serializable {
    /**
     * A HardConstraint is one that every single eventGroup must satisfy.
     * @param eventsToCheckAgainst is the list of Events, each of which representing a hardConstraint.
     * @param evGroupToCheck is the eventGroup to check against.
     * @return whether hard constraint is satisfied or not
     */
    //different Enums, maybe define what each enum does in the Event?
    boolean hardConstraintSatisfied(List<String> eventsToCheckAgainst, EventGroup<E> evGroupToCheck);
}
