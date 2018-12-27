package com.stroncea.androidtimetablescheduler;


import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;


/**
 * Handles Saving and Loading the boardManager
 */
public class SaveAndLoadTimeTableGenerator {

    /**
     * Load the timetable manager from fileName.
     *
     * @param fileName the name of the file
     */
    public static <K extends Event<K>,V extends TimeTable<K,V>, T extends TimeTablesGenerator<K,V>> T loadFromFile(Context context, String fileName) {
        T timetableGenerator = null;
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                timetableGenerator = (T) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return timetableGenerator;
    }

    /**
     * Save the timetable manager to fileName.
     *
     * @param fileName the name of the file
     */
    public static <K extends Event<K>,V extends TimeTable<K,V>, T extends TimeTablesGenerator<K,V>> void saveToFile(Context context, String fileName, T timeTable) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(timeTable);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

