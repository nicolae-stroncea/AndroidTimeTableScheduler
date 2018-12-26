package com.stroncea.androidtimetablescheduler;
import java.util.List;

public class WeeklyTimeTablesGenerator extends TimeTablesGenerator<UofTEvent, WeeklyTimeTable, UofTChoiceOfEventGroups>{
    public WeeklyTimeTablesGenerator(List<UofTChoiceOfEventGroups> buildingBlocks){
        super(buildingBlocks);
    }
    public WeeklyTimeTablesGenerator(){
        super();
    }

    @Override
    public WeeklyTimeTable createTimeTable(List<EventGroup<UofTEvent>> allEvents){
        return new WeeklyTimeTable(allEvents);
    }


}
