package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private Float price;
    private Integer points;
}
