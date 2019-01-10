package com.stroncea.androidtimetablescheduler.HardStrategies;


import com.stroncea.androidtimetablescheduler.Event;
import com.stroncea.androidtimetablescheduler.EventGroup;
import com.stroncea.androidtimetablescheduler.HardConstraints;

public abstract class HardConstraintCheckerFactory<E extends Event<E>, G extends EventGroup<E>> {

    public abstract HardConstraintChecker<E> getHardConstraintChecker(HardConstraints pref);
}
