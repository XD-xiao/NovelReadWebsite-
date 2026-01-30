package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChapterDto {
    private Integer chapterId;
    private String title;
    private String content;

    public UpdateChapterDto(Integer chapterId, String title, String content) {
        this.chapterId = chapterId;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "UpdateChapterDto{" +
                "chapterId=" + chapterId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
