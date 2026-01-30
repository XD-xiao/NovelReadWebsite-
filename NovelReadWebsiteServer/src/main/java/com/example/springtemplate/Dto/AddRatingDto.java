package com.example.springtemplate.Dto;

import lombok.Data;

@Data
public class AddRatingDto {
    private String token;
    private Integer bookId;
    private float rating;
}
