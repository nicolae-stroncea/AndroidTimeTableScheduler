package com.stroncea.androidtimetablescheduler;

import java.util.List;

public interface SoftConstraintStrategy<E extends Event<E>> {
    /**
     * Returns a score for a given user's preference
     * @param valueOfPref represents the value the user assigns to that preference
     * @param events sent here so it's not calculated thousands of times every time the method
     * gets called
     * @return
     */

    int scoreForPreference(int valueOfPref, List<List<E>> events);
}
