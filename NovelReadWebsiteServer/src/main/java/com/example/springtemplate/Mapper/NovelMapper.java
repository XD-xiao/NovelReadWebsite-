package com.example.springtemplate.Mapper;
import com.example.springtemplate.Pojo.Chapter;
import com.example.springtemplate.Pojo.Novel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NovelMapper {

    @Insert("insert into novelTable(cover, title, author, status, lastUpdated, rating, collect, watch, summary, tags) values(#{cover}, #{title}, #{author}, #{status}, #{lastUpdated}, #{rating}, #{collect}, #{watch}, #{summary}, #{tags})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public boolean addBook(Novel novel);

    @Insert("update novelTable set cover = #{cover} where id = #{id}")
    void updateBookCover(String cover, Integer id);

    @Select("select count(*) from novelTable")
    Long getBookListTotal();

    @Select("select * from novelTable limit #{start}, #{pageSize}")
    List<Novel> getBookList(Integer start, Integer pageSize);

    @Update("update novelTable set title = #{title}, author = #{author}, status = #{status}, summary = #{summary}, tags = #{tags} where id = #{id}")
    void updateBookById(Novel novel);

    @Select("select * from novelTable order by watch desc limit #{num}")
    List<Novel> getNovelsByViews(Integer num);

    @Select("select * from novelTable order by rating desc limit #{num}")
    List<Novel> getNovelsByRating(Integer num);

    @Select("select * from novelTable order by collect desc limit #{num}")
    List<Novel> getNovelsByFavorites(Integer num);

    @Select("select * from novelTable where title like concat('%', #{key}, '%') or author like concat('%', #{key}, '%')")
    List<Novel> searchBooks(String key);

    @Select("select * from novelTable where id = #{id}")
    Novel getBookInfo(Integer id);

    @Update("UPDATE novelTable SET watch = watch + 1 WHERE id = #{id}")
    void addWatchCount(Integer id);

    @Update("UPDATE novelTable SET collect = collect + 1 WHERE id = #{id}")
    void addCollectCount(Integer id);

    @Update("UPDATE novelTable SET collect = collect - 1 WHERE id = #{id}")
    void subCollectCount(Integer id);

    @Update("UPDATE novelTable SET rating = #{rating} WHERE id = #{bookId}")
    boolean updateRating(Integer bookId, float rating);

}
