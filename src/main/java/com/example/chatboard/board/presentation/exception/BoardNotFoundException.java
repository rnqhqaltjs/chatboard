package com.example.chatboard.board.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends BusinessException {

    private static final String MESSAGE = "해당 게시물이 존재하지 않습니다.";

    public BoardNotFoundException() {
        super(HttpStatus.NOT_FOUND, MESSAGE);
    }
}