package com.stroncea.androidtimetablescheduler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This describes a list of related EventGroup. For this class, ll connected EventGroups are alternatives to each other, and if a timeTable has 1 connectedEventGroup, it will not have the other.
 *
 * This for example will contain all possible course lecture(where a lecture is an EventGroup(L0101
 * will have Events on Monday, Tuesday Wednesday)..
 * Will Contain for example:CSC208[Lec05,Lec06,Lec07].
 */
public class ChoiceOfEventGroups<E extends Event<E>> implements  Iterable<EventGroup<E>>, Serializable {
    private List<EventGroup<E>> listOfOptions = new ArrayList<>();
    public String name;

    public List<EventGroup<E>> getListOfOptions() {
        return listOfOptions;
    }

    public void setListOfOptions(List<EventGroup<E>> listOfOptions) {
        this.listOfOptions = listOfOptions;
    }

    public void setName(String name) {
        this.name = name;
    }



    /**
     * Adds a section.
     */
    public void add(EventGroup<E> section) {
        listOfOptions.add(section);
    }
    public String getName() {
        return this.name;
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
