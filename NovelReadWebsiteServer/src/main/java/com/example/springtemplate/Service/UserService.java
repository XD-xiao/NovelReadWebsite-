package com.example.springtemplate.Service;

import com.example.springtemplate.Pojo.*;

import java.util.List;
import java.util.Map;


public interface UserService {

    boolean addUser(User user);

    String login(String email);

    PageBean getUserList(Integer pageNum, Integer pageSize);

    User getUserInfo(String token);

    List<Map<String, Object>> getUserViewHistory(String token);

    List<Map<String, Object>> getUserFavorite(String token);

    List<Map<String, Object>> getUserRating(String token);

    boolean addFavoriteService(String token, Integer bookId);

    boolean deleteFavoriteService(String token, Integer bookId);

    boolean addRatingService(String token, Integer bookId, float rating);

    float getBookRatingByUserId(String token, Integer id);

    boolean isBookCollectedByUserId(String token, Integer id);
}
