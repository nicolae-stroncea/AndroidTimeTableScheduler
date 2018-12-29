package com.stroncea.androidtimetablescheduler;

import java.util.List;


//TODO make this a class for TimeTableOnly, get another one for WeeklyTimeTable
public class WeeklyUserPrefStrategies<E extends WeeklyEvent<E>> implements UserPrefStrategies<E, WeeklyTimeTable<E>> {
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
    @Override
    public int scoreForPreference(SoftUserPreference pref, int valueOfPref, WeeklyTimeTable<E> timeTable, List<List<E>> events){
        int score;
        switch(pref){
            case TIME_OF_DAY:
                score = 0;
                int timeOfDay = getTimeOfDay(valueOfPref);
                // Calculate the difference between the start time of each event and the time of day
                for(int i=0; i<events.size(); i++){
                    List<E> eventsByDay =events.get(i);
                    for(int j=1; j<eventsByDay.size();j++){
                        E e = eventsByDay.get(j);
                        // add the difference between the value and the time between events
                        score += Math.abs((e.getStartTime() - timeOfDay));
                        if(score==0){
                            System.out.println("is 0");
                        }
                    }
                }
                return score;
            case NUMBER_OF_DAYS:

                // return the distance between the actual date and the desired date
                return Math.abs(timeTable.getDaysWithEvents() - valueOfPref);
            case TIME_BTN_CLASSES:
                score = 0;
                // Calculate the difference between events in the same Day
                for(int i=0; i<events.size(); i++){
                    List<E> eventsByDay =events.get(i);
                    for(int j=1; j<eventsByDay.size();j++){
                        E e = eventsByDay.get(j);
                        // add the difference between the value and the time between events
                        //TODO remember, i'm assuming here that i get the value in hours
                        score += Math.abs((e.getStartTime()-eventsByDay.get(j-1).getEndTime()) - valueOfPref * 3600);
                    }
                }
                return score;
            default:
                throw new IllegalArgumentException("behaviour for this Enum has not been implemented!");

        }

    }

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
