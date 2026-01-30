package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserInfoDto {
    private String token;

    public GetUserInfoDto(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "GetUserInfoDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
