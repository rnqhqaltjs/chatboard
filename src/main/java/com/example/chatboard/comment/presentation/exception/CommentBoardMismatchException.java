package com.example.chatboard.comment.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommentBoardMismatchException extends BusinessException {

    private static final String MESSAGE = "댓글이 해당 게시글에 속해 있지 않습니다.";

    public CommentBoardMismatchException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE);
    }
}