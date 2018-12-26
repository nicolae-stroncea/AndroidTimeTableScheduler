package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimeTableActivityModel {
    private TimeTablesGenerator<UofTEvent, WeeklyTimeTable,UofTChoiceOfEventGroups> timeTablesGenerator;
    private WeeklyTimeTable weeklyTimeTable;
    private int currTimeTable = 0;

    public TimeTableActivityModel(WeeklyTimeTablesGenerator t){
        this.timeTablesGenerator = t;
        List<WeeklyTimeTable> listOfTimeTables = timeTablesGenerator.getTimeTables();
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
                //TODO if ends at something like:x30 then I'm fucked
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

    public TimeTablesGenerator getTimeTablesGenerator() {
        return timeTablesGenerator;
    }

    public void setTimeTablesGenerator(TimeTablesGenerator<UofTEvent, WeeklyTimeTable,UofTChoiceOfEventGroups> timeTablesGenerator) {
        this.timeTablesGenerator = timeTablesGenerator;
    }

    public WeeklyTimeTable getWeeklyTimeTable() {
        return weeklyTimeTable;
    }

    public void setWeeklyTimeTable(WeeklyTimeTable weeklyTimeTable) {
        this.weeklyTimeTable = weeklyTimeTable;
    }

}
