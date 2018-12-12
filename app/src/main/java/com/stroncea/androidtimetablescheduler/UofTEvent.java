package com.stroncea.androidtimetablescheduler;
public class UofTEvent extends Event<UofTEvent> {
    private String location;
    private String lectureSection;
    private int weekDay;


    public UofTEvent(String name, int startTime, int endTime, int weekDay) {
        super(name, startTime, endTime);
        this.weekDay = weekDay;
    }
    public UofTEvent(){
        super();
    }
    public UofTEvent(String name, int startTime, int endTime,  DaysOfWeek day) {
        super(name, startTime, endTime);
        this.weekDay = day.getDay();
    }

    public UofTEvent(int startTime, int endTime, int weekDay) {
        super("event", startTime, endTime);
        this.weekDay = weekDay;
    }
    public UofTEvent(int startTime, int endTime) {
        super( startTime, endTime);
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
    public void setWeekDay(DaysOfWeek weekDay) {
        this.weekDay = weekDay.getDay();
    }

    public UofTEvent(int startTime) {
        super(startTime, startTime +1);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLectureSection() {
        return lectureSection;
    }

    public void setLectureSection(String lectureSection) {
        this.lectureSection = lectureSection;
    }

    //TODO check logic. See if it works
    @Override
    public boolean intersects(UofTEvent e2) {

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
    public boolean equals(UofTEvent e2){
        boolean equal = false;
        if(this.weekDay == e2.weekDay) {
            if (this.getStartTime() == e2.getStartTime() && this.getEndTime() == e2.getEndTime()){
                equal = true;
            }
        }
        return equal;
    }
    // Events are equal if they have the same startime, endtime, and weekday
    @Override
    public boolean equals(Object obj) {
        boolean isEqual;
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UofTEvent))
            return false;
        UofTEvent other = (UofTEvent) obj;

        isEqual =  equals(other);
        return isEqual;
    }
    @Override
    public int hashCode() {
        return weekDay*1000 + getStartTime()*2 + getEndTime()*6;
    }

}
