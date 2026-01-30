package com.example.springtemplate.Service;


import com.example.springtemplate.Pojo.Notice;
import com.example.springtemplate.Pojo.Result;
import com.example.springtemplate.Pojo.User;

import java.util.List;

public interface NoticeService {
    void addNotice(List<User> users, String title, String content);

    List<Notice> getNotifications(String token);

    boolean getIsNotice(String token);
}
