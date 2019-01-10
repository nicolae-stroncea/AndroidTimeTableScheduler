package com.stroncea.androidtimetablescheduler;

import lombok.Getter;
import lombok.Setter;

public class WeeklyEvent<E extends WeeklyEvent<E>> extends Event<E> {
    @Getter @Setter
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

    public void setWeekDay(DaysOfWeek weekDay) {
        this.weekDay = weekDay.getDay();
    }
    public WeeklyEvent(int startTime) {
        super(startTime, startTime +1);
    }


    @Override
    public boolean intersects(E e2) {

        boolean intersects = false;
        if(this.weekDay == e2.getWeekDay()) {
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


    /**
     * Equals strictly if startTime, endTime and weekDay are the same
     * @param e2
     * @return
     */
    public boolean isEqual(WeeklyEvent<E> e2){
        boolean equal = false;
        if(this.weekDay == e2.weekDay) {
            if (this.getStartTime() == e2.getStartTime() && this.getEndTime() == e2.getEndTime()){
                equal = true;
            }
        }
        return equal;
    }
//     Events are equal if they have the same startime, endtime, and weekday
    @Override
    public boolean equals(Object obj) {
        boolean isEqual;
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof WeeklyEvent))
            return false;
        WeeklyEvent<E> other = (WeeklyEvent<E>) obj;

        isEqual =  isEqual(other);
        return isEqual;
    }
    @Override
    public int hashCode() {
        return weekDay*97 + getStartTime()*11 + getEndTime()*7;
    }
    @Override
    public String toString(){
        String s = super.toString();
        s+=weekDay;
        return s;
    }

}
