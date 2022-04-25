package com.bithumbsystems.bootstarterproject.domain.sample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book {
    @Id
    private String id;
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
