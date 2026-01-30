package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateBookByIdDto {
    private Integer id;
    private String author;
    private String status;
    private String summary;
    private List<String> tags;
    private String title;

    public UpdateBookByIdDto(Integer id, String author, String status, String summary, List<String> tags, String title) {
        this.id = id;
        this.author = author;
        this.status = status;
        this.summary = summary;
        this.tags = tags;
        this.title = title;
    }
    @Override
    public String toString() {
        return "UpdateBookByIdDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                ", tags='" + tags + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
