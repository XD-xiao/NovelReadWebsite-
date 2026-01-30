package com.example.springtemplate.Pojo;


import lombok.Data;

@Data
public class Notice {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private boolean isRead;




}
