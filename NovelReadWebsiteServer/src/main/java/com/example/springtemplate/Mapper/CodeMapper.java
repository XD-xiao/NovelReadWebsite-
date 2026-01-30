package com.example.springtemplate.Mapper;

import com.example.springtemplate.Pojo.Code;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CodeMapper {

    @Insert("insert into codetable(email, code, ttl) values(#{email}, #{code}, #{ttl})")
    public boolean addCode(String email, String code, LocalDateTime ttl);

    @Select("select * from codetable where email = #{email} and code = #{code} and ttl > NOW() limit 1")
    public List<Code> verifyCode(String email, String code);

}
