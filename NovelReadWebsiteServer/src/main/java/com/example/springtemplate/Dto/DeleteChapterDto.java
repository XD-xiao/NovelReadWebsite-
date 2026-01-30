package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteChapterDto {
    private Integer chapterId;

    public DeleteChapterDto(Integer chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        return "DeleteChapterDto{" +
                "chapterId=" + chapterId +
                '}';
    }

}
