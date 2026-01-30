package com.example.springtemplate.Controller;


import com.example.springtemplate.Dto.GetUserInfoDto;
import com.example.springtemplate.Pojo.Notice;
import com.example.springtemplate.Pojo.Result;
import com.example.springtemplate.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/api/notice/getNotifications")
    public Result getNotifications(@RequestBody GetUserInfoDto getUserInfoDto){

        List<Notice> notices =noticeService.getNotifications(getUserInfoDto.getToken());

        return Result.success(notices);
    }

    @PostMapping("/api/notice/getIsNotice")
    public Result getIsNotice(@RequestBody GetUserInfoDto getUserInfoDto){

        boolean isNotice = noticeService.getIsNotice(getUserInfoDto.getToken());

        return Result.success(isNotice);
    }



}
