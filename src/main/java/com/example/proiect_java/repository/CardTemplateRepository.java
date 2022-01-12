package com.example.proiect_java.repository;


import com.example.proiect_java.entity.CardTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTemplateRepository extends MongoRepository<CardTemplate, String> {
    public CardTemplate removeById(String id);
}
