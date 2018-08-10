package io.pivotal.pal.tracker;

//Create a custom counter metric that tracks the number of times
//time entry changes
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final CounterService counter;
    private final GaugeService gauge;

    private TimeEntryRepository timeEntryRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepo,
                               CounterService counter,
                               GaugeService gauge) {

        this.timeEntryRepo = timeEntryRepo;
        this.counter = counter;
        this.gauge = gauge;
    }

    @RequestMapping
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEnt){

        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepo.list().size());

        return new ResponseEntity<TimeEntry>(timeEntryRepo.create(timeEnt), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id){
        TimeEntry timeEnt = timeEntryRepo.find(id);
        if (timeEnt != null){
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(timeEnt, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        counter.increment("TimeEntry.listed");
        ResponseEntity<List<TimeEntry>> toReturn =
                new ResponseEntity<List<TimeEntry>>(timeEntryRepo.list(), HttpStatus.OK);
        return toReturn;
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEnt){
        TimeEntry timeEntToUpdate = timeEntryRepo.update(id, timeEnt);
        if (timeEntToUpdate != null){
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<TimeEntry>(timeEntToUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id){
        timeEntryRepo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepo.list().size());

        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
