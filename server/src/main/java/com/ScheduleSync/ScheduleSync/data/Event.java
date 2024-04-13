package com.ScheduleSync.ScheduleSync.data;

import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.*;


public class Event {

    @Setter
    @Id
    private String eventName;
    @Setter
    private TimeBlock timeBlock;
    @Setter
    private String description;
    private int attending;

    public Event(String eventName, TimeBlock timeBlock, String description) {
        this.eventName = "EVENT: " + eventName;
        this.timeBlock = timeBlock;
        this.description = description;
        this.attending = 0;
    }

    public String getEventName() {
        return eventName;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public String getDescription() {
        return description;
    }

    public int getAttending() {
        return attending;
    }

    public void incrementAttending() {
        this.attending++;
    }

    public void decrementAttending() {
        if (this.attending > 0) {
            this.attending--;
        }
    }
}