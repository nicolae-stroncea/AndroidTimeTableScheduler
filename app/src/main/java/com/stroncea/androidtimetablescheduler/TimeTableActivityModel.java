package com.stroncea.androidtimetablescheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimeTableActivityModel {
    private TimeTablesGenerator<UofTEvent, UofTTimeTable> timeTablesGenerator;
    private UofTTimeTable uofTTimeTable;
    private int currTimeTable = 0;

    public TimeTableActivityModel(UofTTimeTablesGenerator t){
        this.timeTablesGenerator = t;
        List<UofTTimeTable> listOfTimeTables = timeTablesGenerator.getTimeTables();
        uofTTimeTable = listOfTimeTables.get(currTimeTable);
        }
        public List<Integer> getRows(){
            // get the first element
            // get number of rows
            List<UofTEvent> listOfEvents =  uofTTimeTable.getListOfEvents();
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
            List<Integer> listOfHalfHours = new ArrayList<>();
            listOfHalfHours.addAll(setOfHalfHours);
            Collections.sort( listOfHalfHours );
            return listOfHalfHours;
        }
        public void setNextTimeTable(){
            currTimeTable+=1;
            uofTTimeTable = timeTablesGenerator.getTimeTables().get(currTimeTable);
        }
        public void setPrevTimeTable(){
            if(currTimeTable!=0){
                currTimeTable-=1;
                uofTTimeTable = timeTablesGenerator.getTimeTables().get(currTimeTable);
            }
            else{
                uofTTimeTable = timeTablesGenerator.getTimeTables().get(0);

            }

        }

    public TimeTablesGenerator getTimeTablesGenerator() {
        return timeTablesGenerator;
    }

    public void setTimeTablesGenerator(TimeTablesGenerator<UofTEvent, UofTTimeTable> timeTablesGenerator) {
        this.timeTablesGenerator = timeTablesGenerator;
    }

    public UofTTimeTable getUofTTimeTable() {
        return uofTTimeTable;
    }

    public void setUofTTimeTable(UofTTimeTable uofTTimeTable) {
        this.uofTTimeTable = uofTTimeTable;
    }

}
