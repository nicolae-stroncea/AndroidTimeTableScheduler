package com.stroncea.androidtimetablescheduler;


import java.io.Serializable;

public enum DaysOfWeek implements Serializable {
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
    //TODO implement this using ToString instead
    public static String convertNumberToString(int dayOfWeek){
        switch(dayOfWeek){
            case(1):
                return "Monday";
            case(2):
                return "Tuesday";
            case(3):
                return "Wednesday";
            case(4):
                return "Thursday";
            case(5):
                return "Friday";
            default:
                return "Monday";

        }
    }
}
