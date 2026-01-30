package com.example.springtemplate.Service.Impl;

import com.example.springtemplate.Mapper.ChapterMapper;
import com.example.springtemplate.Mapper.NovelMapper;
import com.example.springtemplate.Mapper.WatchMapper;
import com.example.springtemplate.Pojo.Chapter;
import com.example.springtemplate.Pojo.Novel;
import com.example.springtemplate.Pojo.PageBean;
import com.example.springtemplate.Service.NovelService;
import com.example.springtemplate.Utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NovelServiceImpl implements NovelService {

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private WatchMapper watchMapper;
    @Autowired
    private ObjectMapper objectMapper; // 注入 ObjectMapper

    @Override
    public boolean addBook(Novel novel) {

        //"https://picsum.photos/80/100"

        novelMapper.addBook(novel);
        String cover = "https://picsum.photos/80/100?id=" + novel.getId();
        novelMapper.updateBookCover(cover, novel.getId());

        return true;
    }

    @Override
    public PageBean getBookList(Integer pageNum, Integer pageSize) {

        Integer start = (pageNum - 1) * pageSize;
        Long total = novelMapper.getBookListTotal();
        List<Novel> novelList = novelMapper.getBookList(start, pageSize);
        List<Map<String, Object>> resList = new ArrayList<>();
        for (Novel novel : novelList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", novel.getId());
            map.put("cover", novel.getCover());
            map.put("title", novel.getTitle());
            map.put("author", novel.getAuthor());
            map.put("status", novel.getStatus());
            map.put("lastUpdated", novel.getLastUpdated());
            map.put("summary", novel.getSummary());
            List<String> tags = deserializeTags(novel.getTags());
            map.put("tags", tags);
            resList.add(map);
        }

        return new PageBean(total, resList);
    }

    private List<String> deserializeTags(String tagsJson) {
        if (tagsJson == null || tagsJson.trim().equals("[]")) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(tagsJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize tags from JSON: " + tagsJson, e);
        }
    }

    @Override
    public void updateBookById(Novel novel) {
        novelMapper.updateBookById(novel);
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterMapper.addChapter(chapter);

    }

    @Override
    public void deletChapterById(Integer chapterId) {
        chapterMapper.deleteChapterById(chapterId);

    }

    @Override
    public void updateChapterById(Chapter chapter) {
        chapterMapper.updateChapterById(chapter.getId(), chapter.getTitle(), chapter.getContent());
    }

    @Override
    public List<Novel> getNovelsByViews(Integer num) {
        return novelMapper.getNovelsByViews(num);
    }

    @Override
    public List<Novel> getNovelsByRating(Integer num) {
        return novelMapper.getNovelsByRating(num);
    }

    @Override
    public List<Novel> getNovelsByFavorites(Integer num) {
        return novelMapper.getNovelsByFavorites(num);
    }

    @Override
    public List<Novel> searchBooks(String key) {
        return novelMapper.searchBooks(key);
    }

    @Override
    public Novel getBookInfo(Integer id) {
        return novelMapper.getBookInfo(id);
    }

    @Override
    public Chapter getBookContent(String token , Integer chapterId) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");

        watchMapper.addWatch(userID, chapterId , LocalDateTime.now());

        Chapter chapter = chapterMapper.getBookContent(chapterId);
        novelMapper.addWatchCount(chapter.getBookId());

        return chapterMapper.getBookContent(chapterId);
    }

    @Override
    public List<Chapter> getBookChapterList(Integer bookId) {
        return chapterMapper.getBookChapterList(bookId);
    }
}
