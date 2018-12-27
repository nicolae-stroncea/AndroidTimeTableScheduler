package com.stroncea.androidtimetablescheduler;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity implements SwipeGestureCallBack{
    private GestureDetectGridView gridView;
    public static final int NUM_COLS = 6;
    private static int columnWidth, columnHeight;
    public ArrayList<Button> listOfButtons;
    private TimeTableActivityModel activityModel;
    private int rowNumber;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        gridView = findViewById(R.id.grid);
        // Since we have 5 days in a week
        gridView.setNumColumns(NUM_COLS);
        gridView.setSwipeGestureCallBack(this);


        WeeklyTimeTablesGenerator<UofTEvent> t = SaveAndLoadTimeTableGenerator.<UofTEvent, WeeklyTimeTable<UofTEvent>, WeeklyTimeTablesGenerator<UofTEvent>>loadFromFile(this,"TimeTableGenerator");
        t.createTimeTables();
        activityModel = new TimeTableActivityModel(t);

        setUpDisplay();
    }

    private void setUpDisplay() {
        // number of hours
        List<Integer> rows = activityModel.getRows();
        // add 1 because we will have an extra row to display the days of the week
        final int rowNumber =  rows.size() + 1;
        // add one because the first row will be all days of the week
        Button[][] arrayOfButtons= new Button[NUM_COLS][rowNumber];
        Button newBtn;
        int rowTime;
        List<UofTEvent> day;
        // go day by day
        for(int i =0;i<NUM_COLS-1;i++){
            List<List<UofTEvent>> daysWithEvents = activityModel.getWeeklyTimeTable().getEventsByWeek();
            // each day has the events sorted
            day = daysWithEvents.get(i);
            int eventCounter = 0;
            UofTEvent e;
            String text;
            // fill in the buttonArray for 1 day(which represents 1 list)
            // iterate over each index
            for(int j=0;j<rowNumber-1;j++){
                newBtn = new Button(this);
                //get the time which represents this given index
                rowTime = rows.get(j);
                // check if we have an event at this index
                boolean foundIfEventExists = false;
                while(eventCounter<day.size() && !foundIfEventExists){
                    if(day.get(eventCounter).getStartTime()<rowTime){
                        if(day.get(eventCounter).getEndTime()>rowTime){
                            foundIfEventExists=true;
                            newBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

                            e = day.get(eventCounter);
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
                        newBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                        e = day.get(eventCounter);
                        text = e.getName() + " " + e.getLectureSection() + " " + String.valueOf(rowTime/3600);
                        newBtn.setText(text);

                        //however, we don't know anything about whether there are other rows in this event so don't iterate it.

                    }
                    // start time is greater than row time. That means there's no event in list which satisfies this
                    else{
                        foundIfEventExists = true;
                    }
                }
                // we start at 1 as we want our first column to just be the hours
                arrayOfButtons[i+1][j+1] = newBtn;
            }


        }
        // add the time to the first column
        float f;
        String txt;
        for(int i = 0;i<rowNumber-1;i++){
            newBtn = new Button(this);
            // i cast it to float because otherwise it'll truncate result. this way
            // i first cast the integers to float and it'll treat it as a float
            f= (float) rows.get(i)/3600;
            txt = String.valueOf(f);
            if(txt.contains(".0")){
                txt = txt.replace(".0",":00" );
            }
            else{ // must be half an hour
                txt = txt.replace(".5",":30");
            }
            newBtn.setText(txt);
            arrayOfButtons[0][i+1] = newBtn;
        }
        // add the day of the week to the first row
        for(int i = 1;i<NUM_COLS;i++){
            newBtn = new Button(this);
            // i cast it to float because otherwise it'll truncate result. this way
            // i first cast the integers to float and it'll treat it as a float
            newBtn.setText(DaysOfWeek.convertNumberToString(i));
            arrayOfButtons[i][0] = newBtn;
        }
        // the first row, first column will be empty
        arrayOfButtons[0][0] = new Button(this);

        listOfButtons = new ArrayList<>();
        for(int i = 0; i<rowNumber;i++){
            for(int j =0;j<NUM_COLS;j++) {
                listOfButtons.add(arrayOfButtons[j][i]);
            }
        }
        // change the gridView size if the RowNumber is different
        if(rowNumber != this.rowNumber){
            changeGridViewSize(rowNumber);
            this.rowNumber=rowNumber;
        }
        else{
            // don't need to update display in the other one, as it's updated in changeGridViewSize
            setAdapter();
        }
    }

    public void changeGridViewSize(final int rowNumber) {
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        removeOnGlobalLayoutListener(gridView, this);


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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener){
        if (Build.VERSION.SDK_INT < 16) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    @Override
    public void onSwipe(Direction direction) {
        switch(direction){
            case RIGHT:
                activityModel.setPrevTimeTable();
                setUpDisplay();
                break;
            case LEFT:
                activityModel.setNextTimeTable();
                setUpDisplay();
                break;
        }
    }
}
