package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddChapterDto {
    private Integer bookId;
    private String title;
    private String content;

    public AddChapterDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "addChapterDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
