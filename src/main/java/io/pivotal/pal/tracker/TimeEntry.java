package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(){

    }
    public TimeEntry(long id, long pID, LocalDate d){
        this.id = id;
        this.projectId = pID;
        this.date = d;
    }
    public TimeEntry(long pID, long uID, LocalDate d, int h){
        this.projectId = pID;
        this.userId = uID;
        this.date = d;
        this.hours = h;
    }
    public TimeEntry(long id, long pID, long uID, LocalDate d, int h){
        this.id = id;
        this.projectId = pID;
        this.userId = uID;
        this.date = d;
        this.hours = h;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }
    public long getUserId(){
        return this.userId;
    }
    public long getProjectId(){
        return this.projectId;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public int getHours(){
        return this.hours;
    }
    public int getTime(){
        return this.hours;
    }
    public void setTime(int hours){
        this.hours=hours;
    }
    public void setUpdate(TimeEntry timeEnt) {
        this.id = timeEnt.getId();
        this.projectId = timeEnt.getProjectId();
        this.date = timeEnt.getDate();
        this.hours = timeEnt.getHours();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (id != timeEntry.id) return false;
        if (projectId != timeEntry.projectId) return false;
        if (userId != timeEntry.userId) return false;
        if (hours != timeEntry.hours) return false;
        return date != null ? date.equals(timeEntry.date) : timeEntry.date == null;
    }
    @Override
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", hours=" + hours +
                '}';
    }

}
