package com.example.springtemplate.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookChapterDto {
    private Integer bookId;

    public GetBookChapterDto(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "GetBookChapterDto{" +
                "bookId='" + bookId + '\'' +
                '}';
    }
}
