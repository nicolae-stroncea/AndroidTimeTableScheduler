package com.stroncea.androidtimetablescheduler;

public enum TimeOfDay {
    MORNING(0), MIDDAY(1), EVENING(2);
    private final int timeOfDay;   // in kilograms
    TimeOfDay(int time){
        this.timeOfDay = time;
    }
    public static int getTheTimeInSeconds(TimeOfDay e){
        switch(e){
            //return the startTime of the timeOfDay in seconds
            case MORNING:
                return 9*3600;
            case MIDDAY:
                return 13*3600;
            case EVENING:
                return 17*3600;
            default:
                throw new IllegalStateException("Behaviour for this enum has not been declared!");
        }
    }
}
