package com.example.springtemplate.Pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Code {
    private Integer id;
    private String email;
    private String code;
    private LocalDateTime ttl;



}
