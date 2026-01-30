package com.example.springtemplate.Pojo;

import lombok.Data;

@Data
public class Chapter {
    private Integer id;
    private Integer bookId;
    private String title;
    private String content;

    public Chapter(Integer id, Integer bookId, String title, String content) {
        this.id = id;
        this.bookId = bookId;
        this.title = title;
        this.content = content;
    }


}
