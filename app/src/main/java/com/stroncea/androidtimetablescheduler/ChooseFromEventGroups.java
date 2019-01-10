package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Single Responsibility: Represents a list of related EventGroups.
 * For this class, the related EventGroups are all alternatives to each other,
 * and WILL NEVER BE CONSIDERED as part of the same timetable,
 * i.e if a timeTable has 1 ChooseFromEventGroups, it will not have the other.
 *
 * Will Contain for example:CSC208[Lec05,Lec06,Lec07], where a student must be enrolled
 * in STRITLY 1 lecture.
 *
 */
@NoArgsConstructor
public class ChooseFromEventGroups<E extends Event<E>> implements  Iterable<EventGroup<E>>, Serializable {
    private List<EventGroup<E>> listOfOptions = new ArrayList<>();
    @Getter @Setter
    private String name;
    public ChooseFromEventGroups(String name){
        this.name = name;
    }

    /**
     *
     * @return get possible options
     */
    public List<EventGroup<E>> getListOfOptions() {
        return listOfOptions;
    }

    /**
     * Set the possible options
     * @param listOfOptions represents the possible options
     */
    public void setListOfOptions(List<EventGroup<E>> listOfOptions) {
        this.listOfOptions = listOfOptions;
    }

    /**
     * Adds a section.
     */
    public void add(EventGroup<E> section) {
        listOfOptions.add(section);
    }


    public Iterator<EventGroup<E>> iterator() {
        return new SectionIterator();
    }
    public class SectionIterator implements Iterator<EventGroup<E>> {
        int currentSection = 0;
        @Override
        public boolean hasNext(){
            return currentSection< listOfOptions.size();
        }
        @Override
        public EventGroup<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else{
                return listOfOptions.get(currentSection++);

            }


        }
    }
}
