package com.example.springtemplate.Mapper;


import com.example.springtemplate.Pojo.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select * from collecttable where userId = #{userID}")
    List<Collect> getUserFavorite(Integer userID);

    @Insert("insert into collecttable (userId, bookId) values (#{userID}, #{bookId})")
    boolean addCollect(Integer userID, Integer bookId);

    @Delete("delete from collecttable where userId = #{userID} and bookId = #{bookId}")
    boolean deleteCollectById(Integer userID, Integer bookId);

    @Select("select * from collecttable where userId = #{userID} and bookId = #{id}")
    Collect isBookCollectedByUserId(Integer userID, Integer id);
}
