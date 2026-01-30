package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String email;
    private String username;
    private String gender;
    private String verificationCode;

    public RegisterDto() {
    }

    public RegisterDto(String email, String username, String gender, String verificationCode) {
        this.email = email;
        this.username = username;
        this.gender = gender;
        verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", VerificationCode='" + verificationCode + '\'' +
                '}';
    }
}
