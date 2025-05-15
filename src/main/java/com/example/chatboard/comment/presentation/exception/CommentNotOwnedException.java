package com.example.chatboard.comment.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommentNotOwnedException extends BusinessException {

    private static final String MESSAGE = "해당 댓글에 대한 접근 권한이 없습니다.";

    public CommentNotOwnedException() {
        super(HttpStatus.FORBIDDEN, MESSAGE);
    }
}