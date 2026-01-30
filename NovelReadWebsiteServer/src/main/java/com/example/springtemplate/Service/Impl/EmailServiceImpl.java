package com.example.springtemplate.Service.Impl;

import com.example.springtemplate.Pojo.Code;
import com.example.springtemplate.Service.EmailService;
import com.example.springtemplate.Utils.EmailUtil;
import com.example.springtemplate.Mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public String addCode(String email, String typeName) {
        // 生成 4 位数字验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));

        // 发送邮件
        emailUtil.sendEmail(email, "小说阅读网站验证码", "您的" + typeName + "验证码是：" + code);

        // 存入数据库（5 分钟有效）
        LocalDateTime ttl = LocalDateTime.now().plusMinutes(5);
        codeMapper.addCode(email, code, ttl);

        return code;
    }

    @Override
    public boolean verifyCode(String email, String code) {

        System.out.println( email + " | " + code);

        List<Code> codes = codeMapper.verifyCode(email, code);
        return !codes.isEmpty();
    }
}