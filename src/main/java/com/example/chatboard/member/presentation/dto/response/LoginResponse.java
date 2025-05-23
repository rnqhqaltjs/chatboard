package com.example.chatboard.member.presentation.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String accessToken;
    private String refreshToken;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
