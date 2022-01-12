package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    @DBRef
    private List<Card> cards;
}
