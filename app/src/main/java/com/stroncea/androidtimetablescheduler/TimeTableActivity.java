package com.stroncea.androidtimetablescheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TimeTableActivity extends AppCompatActivity {
    private GridView gridView;
    public static final int NUM_COLS = 5;
    private static int columnWidth, columnHeight;
    public ArrayList<Button> listOfButtons;
    private List<List<UofTEvent>> daysWithEvents;
    Button[][] arrayOfButtons;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        gridView = findViewById(R.id.grid);
        // Since we have 5 days in a week
        gridView.setNumColumns(NUM_COLS);


        UofTTimeTablesGenerator t = SaveAndLoadTimeTableGenerator.<UofTEvent, UofTTimeTable, UofTTimeTablesGenerator>loadFromFile(this,"TimeTableGenerator");
        TimeTableActivityModel activityModel = new TimeTableActivityModel(t);

        // number of hours
        List<Integer> rows = activityModel.getRows();
        final int rowNumber =  rows.size();
        arrayOfButtons= new Button[NUM_COLS][rowNumber];
        Button newBtn;
        int rowTime;
        // go day by day
        for(int i =0;i<NUM_COLS;i++){
            daysWithEvents = activityModel.getUofTTimeTable().getEventsByWeek();
            // each day has the events sorted
            List<UofTEvent> day = daysWithEvents.get(i);
            int eventCounter = 0;
            // fill in the buttonArray for 1 day(which represents 1 list)
            // iterate over each index
            for(int j=0;j<rowNumber;j++){
                newBtn = new Button(this);
                //get the time which represents this given index
                rowTime = rows.get(j);
                // check if we have an event at this index
                boolean foundIfEventExists = false;
                while(eventCounter<day.size() && !foundIfEventExists){
                    if(day.get(eventCounter).getStartTime()<rowTime){
                        if(day.get(eventCounter).getEndTime()>rowTime){
                            foundIfEventExists=true;
                            newBtn.setBackgroundColor(R.color.colorPrimary);
                            String text;
                            UofTEvent e = day.get(eventCounter);
                            text = e.getName() + " " + e.getLectureSection() + " " + String.valueOf(rowTime/3600);
                            newBtn.setText(text);
                        }
                        // this means we need another event as endtime is either equal to this row start time
                        // or endtime is less than this row time
                        else{
                            eventCounter+=1;
                        }
                    }
                    // this means we're exactly at the right event

                    else  if(day.get(eventCounter).getStartTime() == rowTime){
                        foundIfEventExists=true;
                        newBtn.setBackgroundColor(R.color.colorPrimary);
                        String text;
                        UofTEvent e = day.get(eventCounter);
                        text = e.getName() + " " + e.getLectureSection() + " " + String.valueOf(rowTime/3600);
                        newBtn.setText(text);

                        //however, we don't know anything about whether there are other rows in this event so don't iterate it.

                    }
                    // start time is greater than row time. That means there's no event in list which satisfies this
                    else{
                        foundIfEventExists = true;
                    }
                }
                arrayOfButtons[i][j] = newBtn;
            }


        }
//        ArrayList<ArrayList<Button>> listOfButtons = new ArrayList<>();
//        ArrayList<Button> oneDay;
//        for(int i =0;i<NUM_COLS;i++){
//            oneDay = new ArrayList<>();
//            for(int j=0;j<rowNumber;j++){
//                oneDay.add(arrayOfButtons[i][j]);
//            }
//            listOfButtons.add(oneDay);
//        }
        listOfButtons = new ArrayList<>();
        for(int i = 0; i<rowNumber;i++){
            for(int j =0;j<NUM_COLS;j++) {
                //TODO fix bug here
                listOfButtons.add(arrayOfButtons[j][i]);
            }
        }
//        for(int i =0;i<NUM_COLS;i++){
//            for(int j=0;j<rowNumber;j++){
//                listOfButtons.add(arrayOfButtons[j][i]);
//            }
//        }
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);


                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / gridView.getNumColumns();
                        columnHeight = displayHeight / rowNumber;


                        setAdapter();
                    }
                });



    }
    public void setAdapter(){
        gridView.setAdapter(new GridAdapter(listOfButtons, columnWidth, columnHeight));
        System.out.println("This should work now");


    }
}
