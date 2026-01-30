package com.example.springtemplate.Service;


public interface EmailService {

    public String addCode(String email, String typeName);

    boolean verifyCode(String email, String code);
}
