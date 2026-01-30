package com.example.springtemplate.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNovelRankingDto {
    private Integer num;

    public GetNovelRankingDto(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "GetNovelRankingDto{" +
                "num=" + num +
                '}';
    }
}
