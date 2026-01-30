package com.example.springtemplate.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVerificationCodeDto {
    private String email;

    public GetVerificationCodeDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GetVerificationCodeDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
