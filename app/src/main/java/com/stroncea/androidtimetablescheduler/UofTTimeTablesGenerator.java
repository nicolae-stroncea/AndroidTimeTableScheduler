package com.stroncea.androidtimetablescheduler;
import java.util.List;

public class UofTTimeTablesGenerator extends TimeTablesGenerator<UofTEvent, UofTTimeTable>{
    public UofTTimeTablesGenerator(List<ChoiceOfEventGroups<UofTEvent>> buildingBlocks){
        super(buildingBlocks);
    }
    public UofTTimeTablesGenerator(){
        super();
    }

    @Override
    public UofTTimeTable createTimeTable(List<List<UofTEvent>> allEvents) {
        return new UofTTimeTable(allEvents);
    }
    // For UofTTimeTable, the timeTable with the smallest score is actually the biggest
    // Therefore, we need to reverse them.
    @Override
    public int compare(UofTTimeTable o1, UofTTimeTable o2){
        int comparingResult = o1.compareTo(o2);
        return -comparingResult;
    }


}
