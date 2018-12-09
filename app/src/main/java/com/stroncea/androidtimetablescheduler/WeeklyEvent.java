package com.stroncea.androidtimetablescheduler;

/**
 * Represetns an event which has weekly properties such as weekday
 */
public class WeeklyEvent extends Event<WeeklyEvent> {
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

    //TODO check logic. See if it works
    @Override
    public boolean intersects(WeeklyEvent e2) {

        boolean intersects = false;
        if(this.weekDay == e2.weekDay) {
            if (this.getStartTime() < e2.getStartTime() && this.getEndTime() > e2.getStartTime()) {
                intersects = true;
            }
            else if(this.getStartTime()==e2.getStartTime()){
                intersects = true;
            }
            else if(this.getStartTime() > e2.getStartTime() && this.getStartTime() < e2.getEndTime()){
                intersects = true;
            }
            // check the opposite
            if (e2.getStartTime() < this.getStartTime() && e2.getEndTime() > this.getStartTime()) {
                intersects = true;
            }
            else if(e2.getStartTime()==this.getStartTime()){
                intersects = true;
            }
            else if(e2.getStartTime() > this.getStartTime() && e2.getStartTime() < this.getEndTime()){
                intersects = true;
            }

        }
        return intersects;
    }

    public int getWeekDay() {
        return weekDay;
    }
}
