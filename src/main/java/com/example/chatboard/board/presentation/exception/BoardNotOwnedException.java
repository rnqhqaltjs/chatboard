package com.example.chatboard.board.presentation.exception;

import com.example.chatboard.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class BoardNotOwnedException extends BusinessException {

    private static final String MESSAGE = "해당 게시글에 대한 접근 권한이 없습니다.";

    public BoardNotOwnedException() {
        super(HttpStatus.FORBIDDEN, MESSAGE);
    }
}