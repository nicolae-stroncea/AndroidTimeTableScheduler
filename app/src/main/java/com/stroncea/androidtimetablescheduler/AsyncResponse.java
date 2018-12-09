package com.stroncea.androidtimetablescheduler;

import java.util.List;

public interface AsyncResponse {
    void processFinish(List<ChoiceOfEventGroups<UofTEvent>> o);
}
