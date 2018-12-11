package com.stroncea.androidtimetablescheduler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UofTTimeTable extends TimeTable<UofTEvent, UofTTimeTable>{
    int daysWithEvents = 0;
    public UofTTimeTable(List<EventGroup<UofTEvent>> listOfEventGroups) {
        super(listOfEventGroups);
    }
    public int getDaysWithEvents(){
        return daysWithEvents;
    }

    /**
     * Returns the events by week, sorted by endtime.
     * @return
     */
    public List<List<UofTEvent>> getEventsByWeek() {
        List<UofTEvent> listOfEvents = new ArrayList<>();
        List<EventGroup<UofTEvent>> listOfEventGroups = getListOfEventGroups();
        // first we flatten the list by combining all the events in 1.
        for (EventGroup<UofTEvent> eventList : listOfEventGroups) {
            listOfEvents.addAll(eventList.getEventGroup());
        }
        List<List<UofTEvent>> eventsByWeek = new ArrayList<>();
        // sort the events by day of the week.
        List<UofTEvent> dayOfWeekEvents;
        for(int i =0; i<5;i++){
            dayOfWeekEvents = new ArrayList<>();
            eventsByWeek.add(dayOfWeekEvents);
        }
        // put each event into a list according to its day of the week
        for(UofTEvent event: listOfEvents){
            switch(event.getWeekDay()){
                case(1):
                    eventsByWeek.get(0).add(event);
                    break;
                case(2):
                    eventsByWeek.get(1).add(event);
                    break;
                case(3):
                    eventsByWeek.get(2).add(event);
                    break;
                case(4):
                    eventsByWeek.get(3).add(event);
                    break;
                case(5):
                    eventsByWeek.get(4).add(event);
                    break;
            }
        }
        // sort each list of events by their endtime
        for(int i=0;i<eventsByWeek.size();i++){
            List<UofTEvent> oneDayEvents = eventsByWeek.get(i);
            oneDayEvents.sort(new Comparator<UofTEvent>() {
                @Override
                public int compare(UofTEvent o1, UofTEvent o2) {

                    if(o1.getEndTime()>o2.getEndTime()){
                        return 1;
                    }
                    else if(o1.getEndTime()<o2.getEndTime()){
                        return -1;
                    }
                    else if(o1.getEndTime()==o2.getEndTime()){
                        throw  new IllegalStateException("endTimes cannot be" +
                                " equal for 2 events in a valid timetable");
                    }
                    else{
                        return 0;
                    }
                }
            });
        }
        return eventsByWeek;
    }

    public void setDaysWithEvents(int daysWithEvents) {
        this.daysWithEvents = daysWithEvents;
    }

    /**
     * Gives a score based on the amount of wait time between classes. The higher the score, the larger the wait time,
     * therefore the worst a timetable is
     * @return
     */
    @Override
    public double giveScore() {
        List<List<UofTEvent>> eventsByWeek = getEventsByWeek();
        // find the number of days which have courses
        for(List<UofTEvent> e: eventsByWeek){
            if(!e.isEmpty()){
                daysWithEvents+=1;
            }
        }
        // Calculate the difference between the times
        int timeDifferenceBtnEvents = 0;
        for(int i=0; i<eventsByWeek.size(); i++){
            List<UofTEvent> eventsByDay =eventsByWeek.get(i);
            for(int j=1; j<eventsByDay.size();j++){
                UofTEvent e = eventsByDay.get(j);
                timeDifferenceBtnEvents += e.getStartTime()-eventsByDay.get(j-1).getEndTime();
            }
        }
        setScore(timeDifferenceBtnEvents);
        return timeDifferenceBtnEvents;
    }
    //TODO this is for just least amount of wait times

    /**
     * TimeTable with the smallest score is the biggest
     * @param o
     * @return
     */
    @Override
    public int compareTo(UofTTimeTable o) {
        if(this.getScore()>o.getScore()){
            return -1;
        }
        else if( this.getScore()==o.getScore()){
            if(this.getDaysWithEvents()<o.getDaysWithEvents()){
                return 1;
            }
            else if(this.getDaysWithEvents()==o.getDaysWithEvents()){
                return 0;
            }
            else{
                return -1;
            }
        }
        else if(this.getScore()< o.getScore()){
            return 1;
        }
        else{
            return 0;
        }
    }


}
