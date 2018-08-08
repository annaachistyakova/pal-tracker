package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
     TimeEntry create(TimeEntry timeEnt);
     TimeEntry find(long id_global);
     TimeEntry update(long idOfTimeEntryToBeUpdated, TimeEntry newInfoForTimeEntry);
     void delete(long id_global);
     List<TimeEntry> list();
}
