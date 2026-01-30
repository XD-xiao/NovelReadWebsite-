package com.example.springtemplate.Dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSearchDto {
    private String key;

    public GetSearchDto(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "GetSearchDto{" +
                "key='" + key + '\'' +
                '}';
    }
}
