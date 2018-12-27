package com.stroncea.androidtimetablescheduler;

import android.os.AsyncTask;

import java.util.List;

import static com.stroncea.androidtimetablescheduler.CourseRequestsModel.request;

/**
 * Make an API Request to get all possible choices for 1 course
 */
class CourseRequests extends AsyncTask<String, Void, List<UofTChooseFromEventGroups>> {
    private Exception exception;
    public AsyncResponse delegate = null;


    /**
     * This gets called when you instantiate the method
     * @param courses
     * @return
     */
    protected List<UofTChooseFromEventGroups> doInBackground(String... courses) {
        try {
            if(courses.length != 1){
                throw new IllegalStateException("courses must  have strictly 1 parameter");
            }
            else{
                return request(courses[0]);
            }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    /**
     * This gets the answer from doInBackground
     * @param
     */

    @Override
    protected void onPostExecute(List<UofTChooseFromEventGroups> result) {
        //passes it on to the activity to deal with it
        delegate.processFinish(result);
    }


}
