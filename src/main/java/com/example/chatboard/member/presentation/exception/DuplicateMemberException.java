package com.example.chatboard.member.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicateMemberException extends BusinessException {

    private static final String MESSAGE = "이미 가입된 이메일입니다.";

    public DuplicateMemberException() {
        super(HttpStatus.CONFLICT, MESSAGE);
    }
}