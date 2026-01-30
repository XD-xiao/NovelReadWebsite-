package com.example.springtemplate.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Watch {
    private Integer id;
    private Integer chapterId;
    private Integer userId;
    private LocalDateTime time;


}
