package com.example.springtemplate.Controller;

import com.example.springtemplate.Dto.*;
import com.example.springtemplate.Pojo.*;
import com.example.springtemplate.Service.NoticeService;
import com.example.springtemplate.Service.NovelService;
import com.example.springtemplate.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private NovelService novelService;
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ObjectMapper objectMapper; // 注入 ObjectMapper

    @PostMapping("/api/bookList")
    public Result getBookList(@RequestBody GetBookListDto getBookListDto) {
        PageBean pageBean = novelService.getBookList(getBookListDto.getPageNum(), getBookListDto.getPageSize());
        return Result.success(pageBean);
    }

    @PostMapping("/api/updateBookById")
    public Result updateBookById(@RequestBody UpdateBookByIdDto updateBookByIdDto) {
        String tagsJson = serializeTags(updateBookByIdDto.getTags());

        Novel novel = new Novel(
                updateBookByIdDto.getId(),
                null,
                updateBookByIdDto.getTitle(),
                updateBookByIdDto.getAuthor(),
                updateBookByIdDto.getStatus(),
                LocalDateTime.now(),
                0,
                0,
                0,
                updateBookByIdDto.getSummary(),
                tagsJson
        );
        novelService.updateBookById(novel);

        return Result.success();
    }

    @PostMapping("/api/addBook")
    public Result addBook(@RequestBody AddBookDto addBookDto) {
        String tagsJson = serializeTags(addBookDto.getTags());

        Novel novel = new Novel(
                null,
                null,
                addBookDto.getTitle(),
                addBookDto.getAuthor(),
                addBookDto.getStatus(),
                LocalDateTime.now(),
                0,
                0,
                0,
                addBookDto.getSummary(),
                tagsJson
        );
        novelService.addBook(novel);

        return Result.success();
    }

    // 辅助方法：安全地将 List<String> 序列化为 JSON 字符串
    private String serializeTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(tags);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize tags to JSON", e);
        }
    }

    @PostMapping("/api/deleteChapter")
    public Result deleteChapter(@RequestBody DeleteChapterDto deleteChapterDto) {
        novelService.deletChapterById(deleteChapterDto.getChapterId());
        return Result.success();
    }

    @PostMapping("/api/updateChapter")
    public Result updateChapter(@RequestBody UpdateChapterDto updateChapterDto) {
        Chapter chapter = new Chapter(
                updateChapterDto.getChapterId(),
                null,
                updateChapterDto.getTitle(),
                updateChapterDto.getContent()
        );
        novelService.updateChapterById(chapter);
        return Result.success();
    }

    @PostMapping("/api/addChapter")
    public Result addChapter(@RequestBody AddChapterDto addChapterDto) {
        Chapter chapter = new Chapter(
                null,
                addChapterDto.getBookId(),
                addChapterDto.getTitle(),
                addChapterDto.getContent()
        );
        novelService.addChapter(chapter);
        return Result.success();
    }

    @PostMapping("/api/getUserList")
    public Result getUserList(@RequestBody GetUserListDto getUserListDto) {
        PageBean pageBean = userService.getUserList(getUserListDto.getPageNum(), getUserListDto.getPageSize());
        return Result.success(pageBean);
    }

    @PostMapping("/api/addNotice")
    public Result addNotice(@RequestBody AddNoticeDto addNoticeDto) {

        for (User user : addNoticeDto.getUserArray()) {
            System.out.println( user);
        }

        noticeService.addNotice(addNoticeDto.getUserArray(), addNoticeDto.getTitle(), addNoticeDto.getContent());
        return Result.success();
    }
}