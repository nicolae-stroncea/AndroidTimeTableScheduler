package com.stroncea.androidtimetablescheduler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void Test(){
        /*
Turn our input into events, and put it into ChooseFromEventGroups's.
Move the ChooseFromEventGroups's to the timeTableGenerator.
TimeTableGenerator takes all appropriate combinations and generates all possible timetables.
Get events List from the Courses, Combine them and Generate a TimeTable.
TimeTable checks if there is a conflict.
 */
//
//    ChooseFromEventGroups<UofTEvent> uofTCourse1 = new ChooseFromEventGroupsWithRepeats("CSC108H1F");
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
//    ChooseFromEventGroupsWithRepeats uofTCourse2 = new ChooseFromEventGroupsWithRepeats("CSCA48");
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
//    ChooseFromEventGroupsWithRepeats uofTCourse3 = new ChooseFromEventGroupsWithRepeats("MGAB01H3");
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
//    ChooseFromEventGroupsWithRepeats uofTCourse4 = new ChooseFromEventGroupsWithRepeats("ENGA02");
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
//    List<ChooseFromEventGroups<UofTEvent>> listOfCourses= new ArrayList<>();
//    listOfCourses.add(uofTCourse1);
//    listOfCourses.add(uofTCourse2);
//    listOfCourses.add(uofTCourse3);
//    listOfCourses.add(uofTCourse4);
//
//
//    TimeTablesGenerator generator = new WeeklyTimeTableCreator(listOfCourses);
//    generator.createTimeTables();
//    generator.getTimeTables().sort(generator);
    }
}