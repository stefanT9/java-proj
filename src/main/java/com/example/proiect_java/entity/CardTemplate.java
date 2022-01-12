package com.example.proiect_java.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CardTemplate {
    @Id
    private String id;
    private String title;
    private String subTitle;
    private Integer maxPoints;
}
