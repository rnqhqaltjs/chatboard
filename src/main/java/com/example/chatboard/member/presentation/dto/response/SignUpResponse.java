package com.example.chatboard.member.presentation.dto.response;

import lombok.Getter;

@Getter
public class SignUpResponse {

    private String email;
    private String nickname;
    private String createdDate;

    public SignUpResponse(String nickname, String email, String createdDate) {
        this.nickname = nickname;
        this.email = email;
        this.createdDate = createdDate;
    }
}
