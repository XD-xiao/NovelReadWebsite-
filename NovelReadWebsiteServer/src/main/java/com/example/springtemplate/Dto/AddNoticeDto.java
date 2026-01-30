package com.example.springtemplate.Dto;

import com.example.springtemplate.Pojo.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddNoticeDto {
    private List<User> userArray;
    private String title;
    private String content;

    public AddNoticeDto(List<User> userArray, String title, String content) {
        this.userArray = userArray;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "AddNoticeDto{" +
                "userArray='" + userArray + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
