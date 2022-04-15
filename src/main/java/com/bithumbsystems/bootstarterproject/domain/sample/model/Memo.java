package com.bithumbsystems.bootstarterproject.domain.sample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Memo {
    @Id
    private int id;
    private String content;
    private int createdAt;
    private boolean deleted;

    public Memo(int id, String content, int createdAt, boolean deleted) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }
}
