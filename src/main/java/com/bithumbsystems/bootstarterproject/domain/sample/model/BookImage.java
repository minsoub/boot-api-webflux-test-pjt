package com.bithumbsystems.bootstarterproject.domain.sample.model;

import lombok.Data;

@Data
public class BookImage {
    private int id;

    private String name;

    public BookImage(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
