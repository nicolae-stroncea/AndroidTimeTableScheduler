package com.stroncea.androidtimetablescheduler.HardStrategies;

import com.stroncea.androidtimetablescheduler.Event;
import com.stroncea.androidtimetablescheduler.EventGroup;
import com.stroncea.androidtimetablescheduler.HardConstraints;

public class HardConstraintCheckerForUofT<E extends Event<E>, G extends EventGroup<E>> extends HardConstraintCheckerFactory<E,G>{

    public  HardConstraintChecker<E> getHardConstraintChecker(HardConstraints pref) {
        switch (pref) {
            case BLOCKED_TIME:
                return new BlockedTimeConstraint<>();
            default:
                return null;
        }
    }

}
