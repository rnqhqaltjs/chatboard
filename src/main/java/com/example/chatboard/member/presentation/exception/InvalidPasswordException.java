package com.example.chatboard.member.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BusinessException {

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다.";

    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, MESSAGE);
    }
}