package com.example.springtemplate.Dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GetBookInfoDto {
    private Integer id;
    private String token;

    public GetBookInfoDto(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetBookInfoDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
