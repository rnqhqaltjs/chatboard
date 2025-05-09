package com.example.chatboard.member.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends BusinessException {

    private static final String MESSAGE = "존재하지 않는 이메일입니다.";

    public NotFoundMemberException() {
        super(HttpStatus.UNAUTHORIZED, MESSAGE);
    }
}