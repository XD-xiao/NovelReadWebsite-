package com.example.springtemplate.Mapper;
import com.example.springtemplate.Pojo.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Insert("insert into noticetable(userId,title,content,isRead) values(#{id},#{title},#{content},#{isRead})")
    void addNotice(Integer id, String title, String content, boolean isRead);

    @Select("select * from noticetable where userID=#{userID}")
    List<Notice> getNotifications(Integer userID);

    @Select("select * from noticetable where userID= #{userID} and isRead=0")
    List<Notice> getIsNotice(Integer userID);
}
