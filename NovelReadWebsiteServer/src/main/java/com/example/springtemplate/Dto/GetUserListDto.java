package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserListDto {
    private Integer pageNum;
    private Integer pageSize;

    public GetUserListDto(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    @Override
    public String toString() {
        return "GetUserListDto{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

}
