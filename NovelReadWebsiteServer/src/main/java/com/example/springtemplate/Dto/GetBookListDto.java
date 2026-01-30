package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookListDto {
    private Integer pageNum;
    private Integer pageSize;

    public GetBookListDto(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    @Override
    public String toString() {
        return "GetBookListDto{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

}
