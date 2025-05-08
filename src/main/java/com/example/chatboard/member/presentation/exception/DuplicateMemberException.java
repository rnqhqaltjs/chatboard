package com.example.chatboard.member.presentation.exception;

public class DuplicateMemberException extends RuntimeException {
    private static final String MESSAGE = "이미 가입된 이메일입니다.";

    public DuplicateMemberException() {
        super(MESSAGE);
    }
}