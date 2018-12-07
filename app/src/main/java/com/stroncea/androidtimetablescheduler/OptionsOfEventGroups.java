package com.stroncea.androidtimetablescheduler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Currently this is the superClass for Course. We want it to represent anything which behaves like course, in the
 * sense that it has mutually exclusive alternatives. We  try and generate timetables using the different alternatives.
 *
 * This for example will contain all possible course lectures.
 * Will Contain for example:CSC208[Lec05,Lec06,Lec07].
 */
public abstract class OptionsOfEventGroups<E extends Event> implements  Iterable<ArrayList<E>> {
    public List<List<E>> getListOfOptions() {
        return listOfOptions;
    }

    public void setListOfOptions(List<List<E>> listOfOptions) {
        this.listOfOptions = listOfOptions;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<List<E>> listOfOptions = new ArrayList<>();
    public String name;
    public abstract boolean equals(Object obj);
    public void add(List<E> Section) {
        listOfOptions.add(Section);
    }
    public String getName() {
        return this.name;
    }
    public Iterator iterator() {
        return new SectionIterator();
    }
    public class SectionIterator implements Iterator {
        int currentSection = 0;
        @Override
        public boolean hasNext(){
            return currentSection< listOfOptions.size();
        }
        @Override
        public List<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else{
                return listOfOptions.get(currentSection++);

            }


        }
    }
}
