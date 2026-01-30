package com.example.springtemplate.Mapper;

import com.example.springtemplate.Pojo.Rating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RatingMapper {
    @Select("select * from ratingtable where userId = #{userID}")
    List<Rating> getUserRating(Integer userID);

    @Insert("insert into ratingtable(userId, bookId, rating) values(#{userID}, #{bookId}, #{rating})")
    boolean addRating(Integer userID, Integer bookId, float rating);

    @Select("select * from ratingtable where bookId = #{id} and userId = #{userID}")
    Rating getBookRatingByUserId(Integer userID, Integer id);

    @Select("select count(*) from ratingtable where bookId = #{id}")
    int getBookRatingCount(Integer id);

    @Select("SELECT COALESCE(SUM(rating), 0) FROM ratingtable WHERE bookId = #{bookId}")
    float getBookRatingSum(Integer bookId);
}
