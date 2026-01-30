package com.example.springtemplate.Pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class User {
    private String name;
    private Integer id;
    private String email;
    private String gender;
    private LocalDateTime createTime;

    public User() {
    }

    public User(String name, Integer id, String email, String gender, LocalDateTime createTime) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.createTime = createTime;
    }

    public User(String name, String email, String gender, LocalDateTime createTime) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
