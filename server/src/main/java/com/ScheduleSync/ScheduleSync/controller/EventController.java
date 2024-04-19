package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.Event;
import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import com.ScheduleSync.ScheduleSync.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public TimeBlock createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return event.getTimeBlock();
    }

    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable String eventId) {
        return eventService.getEventById(eventId);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable String eventId) {
        eventService.deleteEvent(eventId);
    }


}