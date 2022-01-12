package com.example.proiect_java.repository;

import com.example.proiect_java.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {
    public Card removeById(String id);
}
