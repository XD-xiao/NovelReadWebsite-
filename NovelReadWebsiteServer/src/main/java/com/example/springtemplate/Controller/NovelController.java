package com.example.springtemplate.Controller;


import com.example.springtemplate.Dto.*;
import com.example.springtemplate.Pojo.Chapter;
import com.example.springtemplate.Pojo.Novel;
import com.example.springtemplate.Pojo.Result;
import com.example.springtemplate.Service.NovelService;
import com.example.springtemplate.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class NovelController {

    @Autowired
    private NovelService novelService;
    @Autowired
    private ObjectMapper objectMapper; // 注入 ObjectMapper
    @Autowired
    private UserService userService;

    @PostMapping("/api/home/getHomePic")
    public Result getHomePic(){
        ArrayList<String> homePic = new ArrayList<>();
        homePic.add("https://picsum.photos/200/300?random=" + Math.random());
        homePic.add("https://picsum.photos/200/300?random=" + Math.random());
        homePic.add("https://picsum.photos/200/300?random=" + Math.random());
        homePic.add("https://picsum.photos/200/300?random=" + Math.random());

        return Result.success(homePic);
    }

    @PostMapping("/api/home/getNovelsByViews")
    public Result getNovelsByViews(@RequestBody GetNovelRankingDto getNovelRankingDto){

        List<Novel> novels = novelService.getNovelsByViews(getNovelRankingDto.getNum());

        return Result.success(novels);
    }

    @PostMapping("/api/home/getNovelsByRating")
    public Result getNovelsByRating(@RequestBody GetNovelRankingDto getNovelRankingDto){

        List<Novel> novels = novelService.getNovelsByRating(getNovelRankingDto.getNum());

        return Result.success(novels);
    }

    @PostMapping("/api/home/getNovelsByFavorites")
    public Result getNovelsByFavorites(@RequestBody GetNovelRankingDto getNovelRankingDto){

        List<Novel> novels = novelService.getNovelsByFavorites(getNovelRankingDto.getNum());

        return Result.success(novels);
    }

    @PostMapping("/api/home/searchBooks")
    public Result searchBooks(@RequestBody GetSearchDto getSearchDto){

        List<Novel> novels = novelService.searchBooks(getSearchDto.getKey());

        return Result.success(novels);
    }

    @PostMapping("/api/bookInfo")
    public Result getBookInfo(@RequestBody GetBookInfoDto getBookInfoDto){

        Novel novel = novelService.getBookInfo(getBookInfoDto.getId());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("id", novel.getId());
        resMap.put("cover", novel.getCover());
        resMap.put("title", novel.getTitle());
        resMap.put("author", novel.getAuthor());
        resMap.put("status", novel.getStatus());
        resMap.put("lastUpdated", novel.getLastUpdated());
        resMap.put("summary", novel.getSummary());
        List<String> tags = deserializeTags(novel.getTags());
        resMap.put("tags", tags);
        resMap.put("rating", novel.getRating());
        float userRating = userService.getBookRatingByUserId(getBookInfoDto.getToken(), novel.getId());
        resMap.put("userRating" , userRating==-1?null: userRating );
        resMap.put("isCollected" , userService.isBookCollectedByUserId(getBookInfoDto.getToken(), novel.getId()));
        resMap.put("chapters" , novelService.getBookChapterList(getBookInfoDto.getId()));

        return Result.success(resMap);
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

    @PostMapping("/api/bookContent")
    public Result getBookContent(@RequestBody GetBookContentDto getBookContentDto){

        Chapter chapter = novelService.getBookContent(getBookContentDto.getToken(),getBookContentDto.getChapterId());
        return Result.success(chapter);
    }

    @PostMapping("/api/bookChapterList")
    public Result getBookChapterList(@RequestBody GetBookChapterDto getBookChapterDto){
        List<Chapter> chapterList = novelService.getBookChapterList(getBookChapterDto.getBookId());

        return Result.success(chapterList);
    }


}
