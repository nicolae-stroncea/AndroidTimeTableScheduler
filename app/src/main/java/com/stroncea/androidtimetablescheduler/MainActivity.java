package com.stroncea.androidtimetablescheduler;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
private Button addCourse;
private EditText course;
private Button generatetmtblbtn;
private Button clearCourses;
private ListView acceptedCourses;
private List<String> listOfAcceptedCourses = new ArrayList<>();
private RadioGroup semester;
private RadioGroup campus;
private RadioGroup weight;

CourseRequests asyncTask;
ArrayAdapter<String> adapter;
GeneratorWithRepeatingEventGroups<UofTEvent, WeeklyTimeTable<UofTEvent>> t = new GeneratorWithRepeatingEventGroups<>(new WeeklyTimeTableCreator<UofTEvent>());


    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        addCourse = findViewById(R.id.submit);
        generatetmtblbtn = findViewById(R.id.generateTimeTable);
        semester = findViewById(R.id.semester);
        campus = findViewById(R.id.campus);
        weight = findViewById(R.id.weight);
        course = findViewById(R.id.course);
        clearCourses = findViewById(R.id.clearCourses);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfAcceptedCourses);
        acceptedCourses = findViewById(R.id.acceptedCourses);
        acceptedCourses.setAdapter(adapter);
        //this to set delegate/listener back to this class
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String courseCode = course.getText().toString().toUpperCase();
                    courseCode = courseCode.replace(" ","" );
                    Pattern patt = Pattern.compile(".+([HF][135][FSY])$");
                    Matcher m = patt.matcher(courseCode);
                    if(m.matches()){
                        Toast.makeText(MainActivity.this, "Do not types last 3 characters",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String courseWeight;
                        String campusType;
                        String semesterType;
                        int id = weight.getCheckedRadioButtonId();
                        if(id==R.id.half){
                            courseWeight = "H";
                        }
                        else{
                            courseWeight = "Y";
                        }
                        id = campus.getCheckedRadioButtonId();
                        if(id==R.id.UTM){
                            campusType = "5";
                        }
                        else if(id==R.id.UTSC){
                            campusType = "3";
                        }
                        else{
                            campusType = "1";
                        }
                        id = semester.getCheckedRadioButtonId();
                        if(id==R.id.fall){
                            semesterType="F";
                        }
                        else if(id==R.id.winter){
                            semesterType="S";
                        }
                        else{
                            semesterType="Y";

                        }
                        courseCode = courseCode.concat(courseWeight).concat(campusType).concat(semesterType);
                        if(!listOfAcceptedCourses.contains(courseCode)){
                            asyncTask = new CourseRequests();
                            asyncTask.delegate = MainActivity.this;
                            //execute the async task
                            asyncTask.execute(courseCode);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "You have already added this course",Toast.LENGTH_SHORT ).show();
                        }
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
//                TimeTablesGenerator t = new WeeklyTimeTableCreator(listOfCourses);
                SaveAndLoadTimeTableGenerator.saveToFile(MainActivity.this,"TimeTableGenerator",t);
                startActivity(new Intent(MainActivity.this, TimeTableActivity.class));


            }
        });
        clearCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfAcceptedCourses.clear();
                acceptedCourses.setAdapter(adapter);
                t.clearBuildingBlocks();


            }
        });


    }

    /**
     * this gets the result from the CourseRequests
     * @param output is the list of possible choises for a given course:
     *               [Mat235{Lec101,Lec102,Lec103}, Mat235{Tut101,Tut312,TUt301}, Mat{Pra102,}
     */
    @Override
    public void processFinish(List<ChooseFromEventGroupsWithRepeats<UofTEvent>> output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        if(output==null){
            Toast.makeText(this,"Course name wrong" ,Toast.LENGTH_SHORT).show();
        }
        else if(output.size() == 0){
            Toast.makeText(this,"No data for this course available" ,Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this,"Add another course" ,Toast.LENGTH_SHORT).show();
            for(ChooseFromEventGroupsWithRepeats<UofTEvent> option: output){
                // first clean it. Get rid of all repeat times.
                Map<EventGroup<UofTEvent>, List<EventGroup<UofTEvent>>> oneOption = option.stripRepeats();
                t.addToBundle(oneOption);
                t.addBuildingBlocks(option);
            }
            course.getText().clear();
            listOfAcceptedCourses.add(0, output.get(0).getName());
            acceptedCourses.setAdapter(adapter);

        }
    }
}
