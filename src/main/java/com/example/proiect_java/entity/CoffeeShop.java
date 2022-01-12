package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class CoffeeShop {
    @Id
    private String id;
    private String name;
    private String address;
    private String description;
    @DBRef
    private List<Product> products;
    @DBRef
    private List<CardTemplate> cardTemplates;
}
