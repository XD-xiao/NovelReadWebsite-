package com.example.springtemplate.Dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    private String email;
    private String VerificationCode;

    public LoginDto(String email, String verificationCode) {
        this.email = email;
        VerificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + email + '\'' +
                ", VerificationCode='" + VerificationCode + '\'' +
                '}';
    }
}
