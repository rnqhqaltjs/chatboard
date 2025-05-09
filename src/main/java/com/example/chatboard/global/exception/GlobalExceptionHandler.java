package com.example.chatboard.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * API 비즈니스 관련 예외 공동 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDto> handleBusinessException(BusinessException e) {
        log.info("{}: {}", e.getStatus(), e.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(new ExceptionDto(e.getMessage()));
    }
}
