package com.stroncea.androidtimetablescheduler;

import java.util.List;

public class TimeOfDayStrategy<E extends  Event<E>> implements SoftConstraintStrategy<E>
{
    public static final SoftUserPreference pref = SoftUserPreference.TIME_OF_DAY;
    @Override
    public int scoreForPreference(int valueOfPref, List<List<E>> events){
        int score = 0;
        int timeOfDay = getTimeOfDay(valueOfPref);
        // Calculate the difference between the start time of each event and the time of day
        for(int i=0; i<events.size(); i++){
            List<E> eventsByDay =events.get(i);
            for(int j=0; j<eventsByDay.size();j++){
                E e = eventsByDay.get(j);
                // add the difference between the value and the time between events
                score += Math.abs((e.getStartTime() - timeOfDay));
            }
        }
        return score;

    }

    /**
     * Get the time of day in Seconds(morning, midday, or evening)
     * @param valueOfPref is the time of day as an int(0 is morning, 1 is midday, 2 is evening)
     * @return
     */
    private int getTimeOfDay(int valueOfPref) {
        int timeOfDay;
        switch(valueOfPref){
            // Morning
            case 0:
                timeOfDay = TimeOfDay.getTheTimeInSeconds(TimeOfDay.MORNING);
                break;
            // Midday
            case 1:
                timeOfDay = TimeOfDay.getTheTimeInSeconds(TimeOfDay.MIDDAY);
                break;
            // Evening
            case 2:
                timeOfDay = TimeOfDay.getTheTimeInSeconds(TimeOfDay.EVENING);
                break;
            default:
                throw new IllegalArgumentException("Integer can only be 1, 2, or 3!");
        }
        return timeOfDay;
    }
}
