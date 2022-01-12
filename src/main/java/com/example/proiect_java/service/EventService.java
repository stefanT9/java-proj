package com.example.proiect_java.service;

import com.example.proiect_java.entity.CoffeeShop;
import com.example.proiect_java.entity.Event;
import com.example.proiect_java.repository.EventRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event editEvent(String eventId, Event event) {
        Document document = new Document();
        mongoOperations.getConverter().write(event, document);
        Update update = new Update();
        document.forEach(update::set);

        return mongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(eventId)),
                update,
                Event.class);
    }

    public Event removeEvent(String eventId) {
        return eventRepository.removeById(eventId);
    }
}
