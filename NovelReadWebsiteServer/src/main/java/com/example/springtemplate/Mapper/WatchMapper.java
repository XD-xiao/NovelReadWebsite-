package com.example.springtemplate.Mapper;

import com.example.springtemplate.Pojo.Watch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WatchMapper {


    @Select("SELECT * FROM watchtable WHERE userID = #{userID} ORDER BY id DESC")
    List<Watch> getUserViewHistory(Integer userID);

    @Insert("INSERT INTO watchtable (userId, chapterId, time) VALUES (#{userID}, #{chapterId}, #{time})")
    void addWatch(Integer userID, Integer chapterId, LocalDateTime time);
}
