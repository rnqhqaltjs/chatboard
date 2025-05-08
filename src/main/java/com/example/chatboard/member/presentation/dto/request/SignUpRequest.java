package com.example.chatboard.member.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    private String email;
    private String password;
    private String nickname;
    private String createdDate;
}
