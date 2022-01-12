package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Card {
    @Id
    private String id;
    @DBRef
    private CoffeeShop emitter;
    @DBRef
    private CardTemplate template;
    private Integer points;
}
