package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
public class Event {
    @Id
    private String id;
    private String title;
    private Date startDate;
    private Date endDate;
}
