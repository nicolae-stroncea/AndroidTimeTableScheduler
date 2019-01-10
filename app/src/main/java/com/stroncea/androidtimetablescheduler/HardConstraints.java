package com.stroncea.androidtimetablescheduler;

import lombok.Getter;

/**
 * A HardConstraints is one that targets events rather than groupEvents. It defines how some
 * events MUST or MUST not behave.
 */
public enum HardConstraints {

    /**

     *
     *
     * PROPERTIES("Here you specify some hard constraint you want.
     * Not used For constraints based directly on the properties as those are already dealt with
     * by using the getProperty() method of the eventGroup. THis is for more complex preferences
     *
     */
    BLOCKED_TIME("blocked_time");
    // The other 2 are deprecated
    //GROUP_NAME_AND_LECTURE("name_and_lecture"), INSTRUCTOR("instructor");
    @Getter
    String propertyName;
    HardConstraints(String propertyName){
        this.propertyName=propertyName;
    }

}
