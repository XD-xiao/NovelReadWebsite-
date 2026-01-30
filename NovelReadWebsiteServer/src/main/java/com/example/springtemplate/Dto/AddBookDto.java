package com.example.springtemplate.Dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddBookDto {
    private String author;
    private String status;
    private String summary;
    private List<String> tags;
    private String title;

    public AddBookDto(String author, String status, String summary, List<String> tags, String title) {
        this.author = author;
        this.status = status;
        this.summary = summary;
        this.tags = tags;
        this.title = title;
    }

    @Override
    public String toString() {
        return "AddBookDto{" +
                "author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                ", tags='" + tags + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
