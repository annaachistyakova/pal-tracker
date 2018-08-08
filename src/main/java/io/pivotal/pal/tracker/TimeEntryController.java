package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepo) {
        this.timeEntryRepo = timeEntryRepo;
    }

    @RequestMapping
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEnt){
        return new ResponseEntity<TimeEntry>(timeEntryRepo.create(timeEnt), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id){
        TimeEntry timeEnt = timeEntryRepo.find(id);
        if (timeEnt != null){
            return new ResponseEntity<>(timeEnt, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        ResponseEntity<List<TimeEntry>> toReturn =
                new ResponseEntity<List<TimeEntry>>(timeEntryRepo.list(), HttpStatus.OK);
        return toReturn;
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEnt){
        TimeEntry timeEntToUpdate = timeEntryRepo.update(id, timeEnt);
        if (timeEntToUpdate != null){
            return new ResponseEntity<TimeEntry>(timeEntToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id){
        timeEntryRepo.delete(id);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
