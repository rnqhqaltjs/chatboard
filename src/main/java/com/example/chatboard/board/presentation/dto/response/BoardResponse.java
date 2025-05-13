package com.example.chatboard.board.presentation.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private Long id;
    private String title;
    private String content;
    private String nickName;
    private Integer viewCnt;
    private LocalDateTime updatedAt;

    public BoardResponse(Long id, String title, String content, String nickName, Integer viewCnt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickName = nickName;
        this.viewCnt = viewCnt;
        this.updatedAt = updatedAt;
    }
}