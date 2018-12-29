package com.stroncea.androidtimetablescheduler;


import java.util.LinkedHashMap;

public interface Scorable {
    /**
     * is a linkedHashmap of the userPreferences, the order in which they count is the order of priority
     *      i.e SoftUserPreference at index 0 is more important than SoftUserPreference at index 1, which is more
     *      important than at index 2 and so on.
     *      the value of the map is the value they assign to it. For example if they set enum TIME_BTN_CLASSES
     *  be 4, that means they want 4 30-min blocks of free time btn classes
     *
     * @return the scoreForPreference. The best scoreForPreference will be the lowest scoreForPreference.
     */
    void giveScore(LinkedHashMap<SoftUserPreference, Integer> mapOfSoftUserPreferences);
}
