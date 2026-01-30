package com.example.springtemplate.Service.Impl;

import com.example.springtemplate.Mapper.NoticeMapper;
import com.example.springtemplate.Pojo.Notice;
import com.example.springtemplate.Pojo.Result;
import com.example.springtemplate.Pojo.User;
import com.example.springtemplate.Service.NoticeService;
import com.example.springtemplate.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void addNotice(List<User> users, String title, String content) {

        for (User user : users) {
            noticeMapper.addNotice(user.getId(), title, content, false);
        }

    }

    @Override
    public List<Notice> getNotifications(String token) {

        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        return noticeMapper.getNotifications(userID);

    }

    @Override
    public boolean getIsNotice(String token) {
        Map<String, Object> claims = JwtUtil.parseJWT(token);
        Integer userID = (Integer) claims.get("id");
        List<Notice> notices = noticeMapper.getIsNotice(userID);
        System.out.println("notices.size() = " +  notices.size());
        return !notices.isEmpty();
    }
}
