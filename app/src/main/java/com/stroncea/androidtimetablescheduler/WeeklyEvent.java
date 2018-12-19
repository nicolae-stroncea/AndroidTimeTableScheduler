package com.stroncea.androidtimetablescheduler;

/**
 * Represetns an event which has weekly properties such as weekday
 */
public abstract class WeeklyEvent extends Event<WeeklyEvent> {
    private int weekDay;


    public WeeklyEvent(String name, int startTime, int endTime, int weekDay) {
        super(name, startTime, endTime);
        this.weekDay = weekDay;
    }
    public WeeklyEvent(){
        super();
    }
    public WeeklyEvent(String name, int startTime, int endTime,  DaysOfWeek day) {
        super(name, startTime, endTime);
        this.weekDay = day.getDay();
    }

    public WeeklyEvent(int startTime, int endTime, int weekDay) {
        super("event", startTime, endTime);
        this.weekDay = weekDay;
    }
    public WeeklyEvent(int startTime, int endTime) {
        super( startTime, endTime);
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
    public void setWeekDay(DaysOfWeek weekDay) {
        this.weekDay = weekDay.getDay();
    }

    public WeeklyEvent(int startTime) {
        super(startTime, startTime +1);
    }

    public int getWeekDay() {
        return weekDay;
    }
}
