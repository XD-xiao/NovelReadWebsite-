package com.example.springtemplate.Service;


import com.example.springtemplate.Pojo.Chapter;
import com.example.springtemplate.Pojo.Novel;
import com.example.springtemplate.Pojo.PageBean;

import java.util.List;

public interface NovelService {

    boolean addBook(Novel novel);

    PageBean getBookList(Integer pageNum, Integer pageSize);

    void updateBookById(Novel novel);

    void addChapter(Chapter chapter);

    void deletChapterById(Integer chapterId);

    void updateChapterById(Chapter chapter);

    List<Novel> getNovelsByViews(Integer num);

    List<Novel> getNovelsByRating(Integer num);

    List<Novel> getNovelsByFavorites(Integer num);

    List<Novel> searchBooks(String key);

    Novel getBookInfo(Integer id);

    Chapter getBookContent(String token, Integer chapterId);

    List<Chapter> getBookChapterList(Integer bookId);
}
