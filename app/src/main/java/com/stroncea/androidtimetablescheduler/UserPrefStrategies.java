package com.stroncea.androidtimetablescheduler;

import java.util.List;

public interface UserPrefStrategies<E extends Event<E>, T extends TimeTable<E,T>> {
    /**
     *
     * @param pref is the enum which represents a user's soft preference(soft meaning it
     * CAN be fulfilled but DOESN't HAVE TO
     * @param valueOfPref represents the value the user assigns to that preference
     * @param timeTable is the timeTable
     * @param events sent here so it's not calculated thousands of times every time the method
     * gets called
     * @return
     */
    int scoreForPreference(SoftUserPreference pref, int valueOfPref, T timeTable, List<List<E>> events);
}
