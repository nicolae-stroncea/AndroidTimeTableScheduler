package com.stroncea.androidtimetablescheduler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//TODO need to see how to deal with yearly courses
public class CourseRequestsModel {
    private static final String API_KEY = "TngcUaE7v5Pe7rrXDKHmE4qlbfvB01Wz";
    private static final String SEARCH_BY_COURSE = "https://cobalt.qas.im/api/1.0/courses/search?q=";


    /**
     * We will get a string which represents the json of a course like CSC108.
     * This course has a bundle of ChoiceOfEventGroups: because we can have Tutorials, Practicals
     * and lectures, all of which are an EventGroup each.
     * @param course are the arrays of course the user wants executed.
     * @return
     * @throws IOException
     */
    public static List<ChoiceOfEventGroups<UofTEvent>> request(String course) throws IOException {
        String response = sendGET(course);
        List<ChoiceOfEventGroups<UofTEvent>> options = null;
        if(response.equals("[]")){
            System.out.println("REQUEST FAILED");
        }
        else{
            System.out.println("REQUEST SUCCESSFUL");
            //TODO check if it substrings it correctly
            options = processJson(response);
        }
        System.out.println("GET DONE");
        return options;
    }
    //TODO somewhere here need to get rid of bug where a lecture might be completely empty.
    // check wht that's about
    private static List<ChoiceOfEventGroups<UofTEvent>> processJson(String response){
        //
        List<ChoiceOfEventGroups<UofTEvent>> multipleChoices = new ArrayList<>();
        JSONObject meeting_object;
        JSONArray lectureArray;
        UofTEvent e;
        UofTCourse course = null;
        try{
            JSONArray courseAcrossYears = new JSONArray(response);
            boolean foundCurrYear = true;
            int counter = 0;
            String currYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            JSONObject thisYearCourse = new JSONObject();
            while(foundCurrYear && counter<courseAcrossYears.length()){
                thisYearCourse = courseAcrossYears.getJSONObject(counter);
                if(thisYearCourse.getString("term").contains(currYear)){
                    foundCurrYear=true;
                }
                counter+=1;
            }
            if(foundCurrYear){
               EventGroup<UofTEvent> oneLectureGroup;
                // get all the lecture sections and get a list of events for each one
                JSONArray lecture_sections =  thisYearCourse.getJSONArray("meeting_sections");
                // iterate over all the lecture sections
                // there will be different kinds of lecture sections,
                // so we will create an option for each one of them as we are going
                // to need all of them
                Map<Character, ChoiceOfEventGroups<UofTEvent>> typesOfCourses = new HashMap<>();
                String name;
                JSONObject lecture;
//                List<List<UofTEvent>> listOfOptions;
                for (int i=0; i < lecture_sections.length(); i++) {
//                    listOfOptions = new ArrayList<>();
                    meeting_object = lecture_sections.getJSONObject(i);
                    name = meeting_object.getString("code");
                    //This is the array which has all of the meeting times for a specific Lecture section
                    lectureArray = meeting_object.getJSONArray("times");
                    oneLectureGroup  = new EventGroup<>();
                    for (int j=0; j < lectureArray.length(); j++) {
                        lecture = lectureArray.getJSONObject(j);
                        e = new UofTEvent();
                        e.setStartTime(lecture.getInt("start"));
                        e.setEndTime(lecture.getInt("end"));
                        e.setName(thisYearCourse.getString("code"));
                        e.setWeekDay(DaysOfWeek.convertStringToENUM(lecture.getString("day")));
                        e.setLectureSection(name);
                        e.setLocation(lecture.getString("location"));
                        oneLectureGroup.addEvent(e);
                    }
                    char courseType = name.charAt(0);
                    // if this type of lecture already exists, add a new lecture to it
                    if(typesOfCourses.containsKey(courseType)){
                        ChoiceOfEventGroups<UofTEvent> typeCourse = typesOfCourses.get(courseType);
                        typeCourse.add(oneLectureGroup);
                        typesOfCourses.put(courseType, typeCourse);

                    }
                    // if this type of lecture doesn't exists, create it
                    else{
                        ChoiceOfEventGroups<UofTEvent> typeCourse =new UofTCourse();
                        typeCourse.add(oneLectureGroup);
                        typesOfCourses.put(courseType, typeCourse);
                    }
                }
                for(ChoiceOfEventGroups<UofTEvent> choiceOfEventGroups: typesOfCourses.values()){
                    choiceOfEventGroups.setName(thisYearCourse.getString("code"));
                    multipleChoices.add(choiceOfEventGroups);
                }
                System.out.println("Created the list of events");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return multipleChoices;
    }

    /**
     *
     * @param course an array of course names
     * @return a list of json as Strings which we will convert to Events.
     * @throws IOException
     */
    private static String sendGET(String course) throws IOException {
        URL obj;
        HttpURLConnection con;
        String newJSON;
        String url = SEARCH_BY_COURSE.concat(course);
        obj = new URL(url);
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("AUTHORIZATION", API_KEY);
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        // if successful, get the string
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // add the jsonResponse
//            newJSON = response.toString().substring(1,response.toString().length()-1);
            newJSON = response.toString();
            // print result
            System.out.println(newJSON);
        } else {
            // add an error to jSON
            newJSON = "[]";
            System.out.println("GET request not worked");
        }
        return newJSON;
    }
}
