package com.stroncea.androidtimetablescheduler.SoftStrategies;

import com.stroncea.androidtimetablescheduler.Event;
import com.stroncea.androidtimetablescheduler.SoftUserPreference;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SoftConstraintStrategyFactory<E extends Event<E>> implements Serializable {


    /**
     *
     * @return a UserPrefStrategies, which defines how to score data given an enum.
     */
    public SoftConstraintStrategy<E> getUserPreferenceBehaviours(SoftUserPreference pref){
        switch (pref){
            case NUMBER_OF_DAYS:
                return new NumberOfDaysStrategy<>();
            case TIME_BTN_CLASSES:
                return new TimeBetweenClassesStrategy<>();
            case TIME_OF_DAY:
                return new TimeOfDayStrategy<>();
            default:
                throw new IllegalStateException("Behaviour for this enum has not been implemented!");
        }
    }
}
