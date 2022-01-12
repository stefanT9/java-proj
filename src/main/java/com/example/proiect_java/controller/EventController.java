package com.example.proiect_java.controller;

import com.example.proiect_java.entity.Event;
import com.example.proiect_java.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent( event);
    }

    @PutMapping("/{eventId}")
    public Event createEvent(@RequestBody Event event, @PathVariable String eventId){
        return eventService.editEvent(eventId, event);
    }

    @DeleteMapping("/{eventId}")
    public Event createEvent(@PathVariable String eventId){
        return eventService.removeEvent(eventId);
    }

}
