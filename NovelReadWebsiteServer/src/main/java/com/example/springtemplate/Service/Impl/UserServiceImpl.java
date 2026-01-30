package com.example.springtemplate.Service.Impl;

import com.example.springtemplate.Mapper.*;
import com.example.springtemplate.Pojo.*;
import com.example.springtemplate.Service.UserService;
import com.example.springtemplate.Utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WatchMapper watchMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private NovelMapper novelMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public boolean addUser(User user) {

        return userMapper.addUser(
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getCreateTime()
        );

    }

    @Override
    public String login(String email) {

        User user = userMapper.getUserByEmail(email);

        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            return JwtUtil.getJWT(claims);
        }
        return null;
    }

    @Override
    public PageBean getUserList(Integer pageNum, Integer pageSize) {
        Integer start = (pageNum - 1) * pageSize;
        Long total = userMapper.getUserCount();
        List<User> userList = userMapper.getUserList(start, pageSize);

        return new PageBean(total, userList);
    }

    @Override
    public User getUserInfo(String token) {

        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        return userMapper.getUserInfo(userID);
    }

    @Override
    public List<Map<String, Object>> getUserViewHistory(String token) {

        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        List< Watch> watchList = watchMapper.getUserViewHistory(userID);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Watch watch : watchList) {
            int bookId = chapterMapper.getBookContent(watch.getChapterId()).getBookId();
            result.add(Map.of(
                    "id" , watch.getId(),
                    "novelTitle" , novelMapper.getBookInfo(bookId).getTitle(),
                    "chapterTitle" , chapterMapper.getBookContent(watch.getChapterId()).getTitle(),
                    "time" , watch.getTime(),
                    "chapterId" , watch.getChapterId()
            ));
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getUserFavorite(String token) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        List<Collect> collectList = collectMapper.getUserFavorite(userID);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Collect collect : collectList) {
            result.add(Map.of(
                    "id" , collect.getBookId(),
                    "title" , novelMapper.getBookInfo(collect.getBookId()).getTitle(),
                    "author" , novelMapper.getBookInfo(collect.getBookId()).getAuthor()
            ));
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getUserRating(String token) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        List<Rating> ratingList = ratingMapper.getUserRating(userID);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Rating rating : ratingList) {
            result.add(Map.of(
                    "id" , rating.getBookId(),
                    "title" , novelMapper.getBookInfo(rating.getBookId()).getTitle(),
                    "author" , novelMapper.getBookInfo(rating.getBookId()).getAuthor(),
                    "score" , rating.getRating()
            ));
        }

        return result;
    }

    @Override
    public boolean addFavoriteService(String token, Integer bookId) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");

        novelMapper.addCollectCount(bookId);

        return collectMapper.addCollect(userID, bookId);
    }

    @Override
    public boolean deleteFavoriteService(String token, Integer bookId) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");

        novelMapper.subCollectCount(bookId);

        return collectMapper.deleteCollectById(userID, bookId);
    }

    @Override
    public boolean addRatingService(String token, Integer bookId, float rating) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");

        ratingMapper.addRating(userID, bookId, rating);

        float sum = ratingMapper.getBookRatingSum(bookId);
        int count = ratingMapper.getBookRatingCount(bookId);
        float average = 0.0f; // 默认值
        if (count > 0) {
            average = Math.round((sum / count) * 10) / 10.0f;
        }
        return novelMapper.updateRating(bookId, average);
    }

    @Override
    public float getBookRatingByUserId(String token, Integer id) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        Rating rating = ratingMapper.getBookRatingByUserId(userID, id);
        if (rating != null)
            return rating.getRating();
        return -1;
    }

    @Override
    public boolean isBookCollectedByUserId(String token, Integer id) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        Collect collect = collectMapper.isBookCollectedByUserId(userID, id);
        return collect != null;
    }
}
