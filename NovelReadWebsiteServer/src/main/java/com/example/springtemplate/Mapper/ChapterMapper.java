package com.example.springtemplate.Mapper;

import com.example.springtemplate.Pojo.Chapter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChapterMapper {

    @Insert("insert into chapterTable(bookId, title, content) values(#{bookId}, #{title}, #{content})")
    boolean addChapter(Chapter chapter);

    @Delete("DELETE FROM chapterTable WHERE id = #{chapterId}")
    boolean deleteChapterById(Integer chapterId);

    @Update("UPDATE chapterTable SET title = #{title}, content = #{content} WHERE id = #{id}")
    void updateChapterById(Integer id, String title, String content);

    @Select("SELECT * FROM chapterTable WHERE id = #{chapterId}")
    Chapter getBookContent(Integer chapterId);

    @Select("SELECT * FROM chapterTable WHERE bookId = #{bookId}")
    List<Chapter> getBookChapterList(Integer bookId);
}
