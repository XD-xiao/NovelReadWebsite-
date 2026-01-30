package com.example.springtemplate.Pojo;

import lombok.Data;

@Data
public class Rating {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private float rating;
}
