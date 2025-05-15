package com.example.chatboard.comment.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends BusinessException {

    private static final String MESSAGE = "해당 댓글이 존재하지 않습니다.";

    public CommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, MESSAGE);
    }
}