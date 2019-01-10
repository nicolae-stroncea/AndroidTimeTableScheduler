package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class TimeTableActivityModel {
    @Getter @Setter
    private TimeTablesGenerator<UofTEvent, WeeklyTimeTable<UofTEvent>> timeTablesGenerator;
    @Getter @Setter
    private WeeklyTimeTable<UofTEvent> weeklyTimeTable;
    @Getter @Setter
    private int currTimeTable = 0;

    public TimeTableActivityModel(TimeTablesGenerator<UofTEvent,WeeklyTimeTable<UofTEvent>> t){
        this.timeTablesGenerator = t;
        List<WeeklyTimeTable<UofTEvent>> listOfTimeTables = timeTablesGenerator.getTimeTables();
        weeklyTimeTable = listOfTimeTables.get(currTimeTable);
        }
        public List<Integer> getRows(){
            // get the first element
            // get number of rows
            List<UofTEvent> listOfEvents =  weeklyTimeTable.getListOfEvents();
            Set<Integer> setOfHalfHours = new HashSet<>();
            for(UofTEvent event:listOfEvents) {
                // add the first hour
                int durationInSeconds = event.getEndTime() - event.getStartTime();
                int numberOfHalfHours = durationInSeconds / 1800;
                int newHour = event.getStartTime();
                for (int i = 0; i < numberOfHalfHours; i++) {
                    setOfHalfHours.add(newHour + i * 1800);
                }
            }
            List<Integer> listOfHalfHours = new ArrayList<>(setOfHalfHours);
            Collections.sort( listOfHalfHours );
            return listOfHalfHours;
        }
        public void setNextTimeTable(){
            currTimeTable+=1;
            weeklyTimeTable = timeTablesGenerator.getTimeTables().get(currTimeTable);
        }
        public void setPrevTimeTable(){
            if(currTimeTable!=0){
                currTimeTable-=1;
                weeklyTimeTable = timeTablesGenerator.getTimeTables().get(currTimeTable);
            }
            else{
                weeklyTimeTable = timeTablesGenerator.getTimeTables().get(0);

            }

        }

}
