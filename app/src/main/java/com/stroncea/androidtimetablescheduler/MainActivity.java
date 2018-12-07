package com.stroncea.androidtimetablescheduler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements AsyncResponse {
private Button addCourse;
private EditText course;
private Button generatetmtblbtn;
private ListView acceptedCourses;
private List<String> listOfAcceptedCourses = new ArrayList<>();
CourseRequests asyncTask;
ArrayAdapter<String> adapter;
UofTTimeTablesGenerator t = new UofTTimeTablesGenerator();


    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        addCourse = findViewById(R.id.submit);
        generatetmtblbtn = findViewById(R.id.generateTimeTable);
        course = findViewById(R.id.course);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfAcceptedCourses);
        acceptedCourses = findViewById(R.id.acceptedCourses);
        acceptedCourses.setAdapter(adapter);
        //this to set delegate/listener back to this class
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String courseCode = course.getText().toString().toUpperCase();
                    if(!listOfAcceptedCourses.contains(courseCode)){
                        asyncTask = new CourseRequests();
                        asyncTask.delegate = MainActivity.this;
                        //execute the async task
                        asyncTask.execute(courseCode);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You have already added this course",Toast.LENGTH_SHORT ).show();;
                    }


                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        generatetmtblbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TimeTablesGenerator t = new UofTTimeTablesGenerator(listOfCourses);

                t.createTimeTables();
                t.getTimeTables().sort(t);
                List<List<UofTEvent>> eventsSorted = t.getTimeTables().get(0).getEventsByWeek();
                SaveAndLoadTimeTableGenerator.saveToFile(MainActivity.this,"TimeTableGenerator",t);
                startActivity(new Intent(MainActivity.this, TimeTableActivity.class));

            }
        });

/*
Turn our input into events, and put it into OptionsOfEventGroups's.
Move the OptionsOfEventGroups's to the timeTableGenerator.
TimeTableGenerator takes all appropriate combinations and generates all possible timetables.
Get events List from the Courses, Combine them and Generate a TimeTable.
TimeTable checks if there is a conflict.
 */
//
//    OptionsOfEventGroups<UofTEvent> uofTCourse1 = new UofTCourse("CSC108H1F");
//
//    List<UofTEvent> listOfEvents = new ArrayList<>();
//    UofTEvent e;
//
//    e = new UofTEvent("CSC108Lec01",12,13,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSC108Lec01",11,13,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse1.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSC108Lec02",12,13,DaysOfWeek.FRIDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSC108Lec02",13,15,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse1.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSC108Lec03",15,16,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSC108Lec03",15,17,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse1.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSC108Lec04",16,17,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSC108Lec04",15,17,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse1.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSC108Lec05",13,14,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSC108Lec05",13,15,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse1.add(listOfEvents);
//
//    UofTCourse uofTCourse2 = new UofTCourse("CSCA48");
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSCA48Lec01",13,14,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSCA48Lec01",10,12,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse2.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSCA48Lec02",12,13,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSCA48Lec02",11,1,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse2.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSCA48Lec03",15,16,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSCA48Lec03",15,17,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse2.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("CSCA48Lec04",16,17,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("CSCA48Lec04",15,17,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse2.add(listOfEvents);
//
//    UofTCourse uofTCourse3 = new UofTCourse("MGAB01H3");
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec01",9,11,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec02",12,14,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec03",9,11,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec04",11,13,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec05",11,13,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec06",11,13,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec07",16,17,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("MGAB01H3Lec08",13,15,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    uofTCourse3.add(listOfEvents);
//
//    UofTCourse uofTCourse4 = new UofTCourse("ENGA02");
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("ENGA02Lec01",11,13,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("ENGA02Lec01",11,13,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse4.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("ENGA02Lec02",13,15,DaysOfWeek.MONDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("ENGA02Lec02",13,15,DaysOfWeek.WEDNESDAY);
//    listOfEvents.add(e);
//    uofTCourse4.add(listOfEvents);
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("ENGA02Lec03",13,15,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("ENGA02Lec03",13,15,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse4.add(listOfEvents);
//
//
//    listOfEvents = new ArrayList<>();
//    e = new UofTEvent("ENGA02Lec04",15,17,DaysOfWeek.TUESDAY);
//    listOfEvents.add(e);
//    e = new UofTEvent("ENGA02Lec04",15,17,DaysOfWeek.THURSDAY);
//    listOfEvents.add(e);
//    uofTCourse4.add(listOfEvents);
//
//    List<OptionsOfEventGroups<UofTEvent>> listOfCourses= new ArrayList<>();
//    listOfCourses.add(uofTCourse1);
//    listOfCourses.add(uofTCourse2);
//    listOfCourses.add(uofTCourse3);
//    listOfCourses.add(uofTCourse4);
//
//
//    TimeTablesGenerator t = new UofTTimeTablesGenerator(listOfCourses);
//    t.createTimeTables();
//    t.getTimeTables().sort(t);
    }

    //this gets the result from the CourseRequests
    @Override
    public void processFinish(OptionsOfEventGroups<UofTEvent> output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        if(output==null){
            Toast.makeText(this,"Course name wrong" ,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Add another course" ,Toast.LENGTH_SHORT).show();
            t.addBuildingBlocks(output);
            listOfAcceptedCourses.add(output.getName());
            acceptedCourses.setAdapter(adapter);

        }
    }

}
