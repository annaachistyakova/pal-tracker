package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
     TimeEntry create(TimeEntry timeEnt);
     TimeEntry find(Long id_global);
     TimeEntry update(Long idOfTimeEntryToBeUpdated, TimeEntry newInfoForTimeEntry);
     void delete(Long id_global);
     List<TimeEntry> list();
}
