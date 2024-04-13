package com.ScheduleSync.ScheduleSync.service;

import com.ScheduleSync.ScheduleSync.data.Event;
import com.ScheduleSync.ScheduleSync.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {

        return eventRepository.save(event);
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    public void incrementAttending(String id) {
        Event event = getEventById(id);
        event.incrementAttending();
        updateEvent(event);
    }

    public void decrementAttending(String id) {
        Event event = getEventById(id);
        if (event.getAttending() > 0) {
            event.decrementAttending();
            updateEvent(event);
        }
    }
}