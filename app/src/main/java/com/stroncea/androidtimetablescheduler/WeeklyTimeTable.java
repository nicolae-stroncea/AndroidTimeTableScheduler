package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeeklyTimeTable<E extends WeeklyEvent<E>> extends TimeTable<E, WeeklyTimeTable<E>>{
    private int daysWithEvents = 0;
    public WeeklyTimeTable(List<EventGroup<E>> listOfEventGroups) {
        super(listOfEventGroups);
        List<List<E>> eventsByWeek = getListOfDayEvents();
        // find the number of days which have courses
        for(List<E> e: eventsByWeek){
            if(!e.isEmpty()){
                daysWithEvents+=1;
            }
        }
    }
    /**
     *
     * @return the events by week, sorted by endtime.
     */
    @Override
    public List<List<E>> getListOfDayEvents() {
        List<E> listOfEvents = new ArrayList<>();
        List<EventGroup<E>> listOfEventGroups = getListOfEventGroups();
        // first we flatten the list by combining all the events in 1.
        for (EventGroup<E> eventList : listOfEventGroups) {
            listOfEvents.addAll(eventList.getEventGroup());
        }
        List<List<E>> eventsByWeek = new ArrayList<>();
        // sort the events by day of the week.
        List<E> dayOfWeekEvents;
        for(int i =0; i<5;i++){
            dayOfWeekEvents = new ArrayList<>();
            eventsByWeek.add(dayOfWeekEvents);
        }
        // put each event into a list according to its day of the week
        for(E event: listOfEvents){
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
            List<E> oneDayEvents = eventsByWeek.get(i);
            Comparator<E> comparator = new Comparator<E>() {
                @Override
                public int compare(E o1, E o2) {

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
            };
            Collections.sort(oneDayEvents, comparator);

        }
        return eventsByWeek;
    }



    @Override
    public UserPrefStrategies<E,WeeklyTimeTable<E>> getUserPreferenceBehaviours(){
        return new WeeklyUserPrefStrategies<E>(this);
    }
    public int getDaysWithEvents(){
        return daysWithEvents;
    }
    public void setDaysWithEvents(int daysWithEvents) {
        this.daysWithEvents = daysWithEvents;
    }

}

