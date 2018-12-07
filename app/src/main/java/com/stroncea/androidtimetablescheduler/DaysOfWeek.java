package com.stroncea.androidtimetablescheduler;


public enum DaysOfWeek {
    MONDAY(1),TUESDAY(2), WEDNESDAY(3),THURSDAY(4),FRIDAY(5);
    private int day;
    DaysOfWeek(int day){
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public static DaysOfWeek convertStringToENUM(String dayOfWeek){
        dayOfWeek = dayOfWeek.toUpperCase();
        switch(dayOfWeek){
            case("MONDAY"):
                return MONDAY;
            case("TUESDAY"):
                return TUESDAY;
            case("WEDNESDAY"):
                return WEDNESDAY;
            case("THURSDAY"):
                return THURSDAY;
            case("FRIDAY"):
                return FRIDAY;
            default:
                return MONDAY;

        }
    }
}
