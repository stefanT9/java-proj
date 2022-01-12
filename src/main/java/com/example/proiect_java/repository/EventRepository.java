package com.example.proiect_java.repository;

import com.example.proiect_java.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    public Event removeById(String id);
}
