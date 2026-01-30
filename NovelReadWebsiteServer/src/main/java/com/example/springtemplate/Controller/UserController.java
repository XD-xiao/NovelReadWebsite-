package com.example.springtemplate.Controller;


import com.example.springtemplate.Dto.AddRatingDto;
import com.example.springtemplate.Dto.GetUserInfoDto;
import com.example.springtemplate.Dto.UpdateCollectDto;
import com.example.springtemplate.Pojo.*;
import com.example.springtemplate.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/getUserInfo")
    public Result getUserInfo(@RequestBody GetUserInfoDto getUserInfoDto ){

        User user = userService.getUserInfo(getUserInfoDto.getToken());

        return Result.success(user);
    }

    @PostMapping("/api/user/getUserViewHistory")
    public Result getUserViewHistory(@RequestBody GetUserInfoDto getUserInfoDto){

        return Result.success(userService.getUserViewHistory(getUserInfoDto.getToken()));
    }

    @PostMapping("/api/user/getUserFavorite")
    public Result getUserFavorite(@RequestBody GetUserInfoDto getUserInfoDto){

        return Result.success(userService.getUserFavorite(getUserInfoDto.getToken()));
    }

    @PostMapping("/api/user/getUserRating")
    public Result getUserRating(@RequestBody GetUserInfoDto getUserInfoDto){

        return Result.success(userService.getUserRating(getUserInfoDto.getToken()));
    }

    @PostMapping("/api/user/addFavoriteService")
    public Result addFavoriteService(@RequestBody UpdateCollectDto updateCollectDto){

        return Result.success(userService.addFavoriteService(updateCollectDto.getToken() , updateCollectDto.getBookId()));
    }

    @PostMapping("/api/user/deleteFavoriteService")
    public Result deleteFavoriteService(@RequestBody UpdateCollectDto updateCollectDto){

        return Result.success(userService.deleteFavoriteService(updateCollectDto.getToken() , updateCollectDto.getBookId()));
    }

    @PostMapping("/api/user/addRatingService")
    public Result addRatingService(@RequestBody AddRatingDto addRatingDto){

        return Result.success(userService.addRatingService(addRatingDto.getToken() , addRatingDto.getBookId() , addRatingDto.getRating()));
    }

}
