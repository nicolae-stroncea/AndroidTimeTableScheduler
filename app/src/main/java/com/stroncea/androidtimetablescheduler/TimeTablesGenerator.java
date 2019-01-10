package com.stroncea.androidtimetablescheduler;

import com.stroncea.androidtimetablescheduler.HardStrategies.HardConstraintChecker;
import com.stroncea.androidtimetablescheduler.SoftStrategies.SoftConstraintStrategyFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
Generates all possible TimeTables given a list  of choice of EventGroups.
 */
public class TimeTablesGenerator<E extends Event<E>, T extends TimeTable<E,T>> implements  Serializable {
    /**
     * is a map of the userPreferences, the order in which they count is the order of priority
     * i.e SoftUserPreference at index 0 is more important than SoftUserPreference at index 1, which is more
     * important than at index 2 and so on.
     * the value of the map is the value they assign to it. For example if they set enum TIME_BTN_CLASSES
     * be 4, that means they want 4 30-min blocks of free time btn classes
     */
    //TODO refactor the map so that when you shove 1hr blocks, it doubles it to account for fact that
    //we're adding half hour slots rather than 1 hour slots

    @Getter
    @Setter
    private LinkedHashMap<SoftUserPreference, Integer> softOrderingConstraints = new LinkedHashMap<>();
    @Getter
    @Setter
    private SoftConstraintStrategyFactory<E> softConstraintStrategyFactory;

    /**
     * Complex Preferences which MUST be satisfied. If they're not, then they're not a valid timeTable.
     */
    @Getter
    @Setter
    private Map<HardConstraintChecker<E>, List<String>> hardConstraints = new HashMap<>();

    /**
     * Properties of an EventGroup which must be satisfied. Property is the key,
     * and the list of values chosen is the respective value.
     * Example: {Instructor, {Anna Beorj, Jason Bourne}}
     */
    @Getter
    @Setter
    private Map<String, List<String>> groupEventProperties = new HashMap<>();


    @Getter
    @Setter
    private TimeTableCreator<E, T> timeTableCreator;

    @Getter
    @Setter
    private List<ChooseFromEventGroups<E>> buildingBlocks;
    @Getter
    @Setter
    private List<T> timeTables = new ArrayList<>();



    /**
     * Gets passed a list of ChooseFromEventGroups's, which are objects that contain alternatives for a course, i.e
     * Lec101, Lec205, Lec104 for a course CSC108.
     *
     * @param buildingBlocks is a list of ChooseFromEventGroups to be combined together.
     */
    public TimeTablesGenerator(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E, T> t) {
        this.timeTableCreator = t;
        this.buildingBlocks = buildingBlocks;

    }

    public TimeTablesGenerator(TimeTableCreator<E, T> t) {
        this.timeTableCreator = t;
        this.buildingBlocks = new ArrayList<>();
    }

    public TimeTablesGenerator(List<ChooseFromEventGroups<E>> buildingBlocks, TimeTableCreator<E, T> t, LinkedHashMap<SoftUserPreference, Integer> softOrderingConstraints) {
        this(buildingBlocks, t);
        this.softOrderingConstraints = softOrderingConstraints;
    }

    public TimeTablesGenerator() {

    }

    /**
     * Create all possible TimeTable Combinations and sorts them according to scoreForPreference
     */
    //TODO transform this into normal forLoops. It's a lot faster that way.
    public void createTimeTables() {
        if (buildingBlocks.size() != 0) {
            createHelper(0, new ArrayList<EventGroup<E>>());
            Collections.sort(timeTables);
        }
    }

    /**
     * Let's say we have: [CSC108[x1,x2],Mat235[y1,y2],CSC165[z1].
     * We would build timetable: [x1,y1,z1],[x1,y2,z1],[x2,y1,z1],[x2,y2,z1].
     *
     * @param index within buildingBlocks which shows at which UnkownP we are currently at.
     * @param items is a List of ChooseFromEventGroups's in this combination.
     */
    private void createHelper(int index, List<EventGroup<E>> items) {
        // If we're at the last b1uildingBlock in the list
        if (index == buildingBlocks.size() - 1) {
            for (EventGroup<E> e : buildingBlocks.get(index)) {
                items.add(e);
                // create a new arrayList with items
                // because otherwise the current one is modified
                if (!hasHardConflict(new ArrayList<>(items))) {
                    T t = timeTableCreator.createTimeTable(new ArrayList<>(items));
                    t.giveScore(softOrderingConstraints, softConstraintStrategyFactory);
                    timeTables.add(t);

                }
                items.remove(items.size() - 1);
            }
        } else {
            // Add all the events from 1
            for (EventGroup<E> e : buildingBlocks.get(index)) {
                items.add(e);
                createHelper(index + 1, items);
                items.remove(items.size() - 1);
            }

        }
    }


    public void clearBuildingBlocks() {
        buildingBlocks.clear();
    }


    public void addBuildingBlocks(ChooseFromEventGroups<E> newBuildingBlock) {
        buildingBlocks.add(newBuildingBlock);
    }


    /**
     * We have a list of a EventGroups. Within each EventGroup, we know that all the events do not have a conflict
     * with one another. Therefore we will test every event in 1 list of events against every event in another list of events
     * Identify if you have a conflict between events. Assumes all of them may have a conflict between EachOther
     *
     * @param listOfEventGroups is a list of EventGroups, where all Events in an EventGroup are related to each other. listOfEventGroups is all the events that are part of this timeTable
     */
    public boolean hasHardConflict(List<EventGroup<E>> listOfEventGroups) {
        List<E> allEvents = new ArrayList<>();
        for (EventGroup<E> eventList : listOfEventGroups) {
            allEvents.addAll(eventList.getEventGroup());
        }
//        if(!satisfiesGroupEventProperties(listOfEventGroups)){
//            return false;
//        }
//        if(!satisfiesHardConstraints(listOfEventGroups)){
//            return false;
//        }

        // First do the hard constraints as it's less computationally intensive then
        // comparing all events for conflicts

        for (int i = 0; i < allEvents.size(); i++) {
            for (int j = i + 1; j < allEvents.size(); j++) {
                if (allEvents.get(i).intersects(allEvents.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param listEvGroups
     * @return
     */
    public boolean satisfiesHardConstraints(List<EventGroup<E>> listEvGroups) {
        // iterate over each eventGroup and get the number of constraints it satisfies
        for (EventGroup<E> eventGroup : listEvGroups) {
            Set<HardConstraintChecker<E>> setOfConstraints = hardConstraints.keySet();
            for (HardConstraintChecker<E> hcc : setOfConstraints) {
                if (!hcc.hardConstraintSatisfied(hardConstraints.get(hcc), eventGroup))
                    return false;
            }
        }

        return true;
    }

        /**
         * @param listEvGroups is the list of eventGroups being analyzed by the timeTableGenerator
         * for a possible instance of a timetable
         * @return whether the list of these eventGroups satisfy all the hardProperties the user wants.
         */
        public boolean satisfiesGroupEventProperties(List <EventGroup<E>> listEvGroups){
            // make sure that the list cumulatively satisfies all constraints
            int numConstraints = 0;
            int totalSatisfied = 0;
            //get the number of constraints to satisfy
            Collection<List<String>> allConstraints = groupEventProperties.values();
            for (List<String> ls : allConstraints) {
                numConstraints += allConstraints.size();
            }
            Set<Entry<String, List<String>>> entries = groupEventProperties.entrySet();
            // analyze every event in the list
            for (EventGroup<E> evGroup : listEvGroups) {
                // check for every property
                for (Entry<String, List<String>> entry : entries) {
                    String property = entry.getKey();
                    List<String> values = entry.getValue();
                    for (String value : values) {
                        if (evGroup.getPropertyValue(property).equals(value)) {
                            totalSatisfied += 1;
                            // we break out of this for loop because if we've satisfied one property,
                            // we cannot and should not satisfy the others
                            //TODO make sure it breaks appropriately
                            break;
                        }
                    }
                }
            }


            return numConstraints == totalSatisfied;
        }
}

