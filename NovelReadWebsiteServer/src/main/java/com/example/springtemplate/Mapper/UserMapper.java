package com.example.springtemplate.Mapper;
import com.example.springtemplate.Pojo.User;
import com.example.springtemplate.Pojo.Watch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {


    @Insert("insert into usertable(name, email, gender, createTime) values(#{name}, #{email}, #{gender}, #{createTime})")
    public boolean addUser(String name, String email, String gender, LocalDateTime createTime);

    @Select("select * from usertable where email = #{email}")
    public User getUserByEmail(String email);

    @Select("select count(*) from usertable")
    Long getUserCount();

    @Select("select * from usertable limit #{start}, #{pageSize}")
    List<User> getUserList(Integer start, Integer pageSize);

    @Select("select * from usertable where id = #{userID}")
    User getUserInfo(Integer userID);


}
