package io.pivotal.pal.tracker;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private ArrayList<TimeEntry> timeEntries = new ArrayList<>();

    @Override
    public TimeEntry create(TimeEntry timeEnt){
        Long id = timeEntries.size() + 1L;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEnt.getProjectId(),
                timeEnt.getUserId(),
                timeEnt.getDate(),
                timeEnt.getTime()
        );
        timeEntries.add(newTimeEntry);
        return newTimeEntry;
    }
    @Override
    public TimeEntry find(long id_global){
        for (int i = 0; i < timeEntries.size(); i++) {
            if(timeEntries.get(i).getId() == id_global){
                return timeEntries.get(i);
            }
        }
        return null;
    }
    @Override
    public TimeEntry update(long idOfTimeEntryToBeUpdated, TimeEntry newInfoForTimeEntry){
        TimeEntry timeEntryToBeUpdated = new TimeEntry(
                idOfTimeEntryToBeUpdated,
                newInfoForTimeEntry.getProjectId(),
                newInfoForTimeEntry.getUserId(),
                newInfoForTimeEntry.getDate(),
                newInfoForTimeEntry.getTime()
        );
        timeEntries.set(getIndex(idOfTimeEntryToBeUpdated),timeEntryToBeUpdated);
        return timeEntryToBeUpdated;

    }
    @Override
    public void delete(long id_global){
        timeEntries.remove(find(id_global));
    }
    @Override
    public List<TimeEntry> list(){
        return timeEntries;
    }
    public int getIndex(long id_global){
        for (int i = 0; i < timeEntries.size(); i++) {
            if(timeEntries.get(i).getId() == id_global){
                return i;
            }
        }
        return -1;
    }
}
