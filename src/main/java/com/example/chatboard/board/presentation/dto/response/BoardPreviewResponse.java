package com.example.chatboard.board.presentation.dto.response;

import com.example.chatboard.board.domain.model.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardPreviewResponse {

    private Long id;
    private String title;
    private String nickName;
    private Integer viewCnt;
    private LocalDateTime createdAt;

    public BoardPreviewResponse(Long id, String title, String nickName, Integer viewCnt, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.nickName = nickName;
        this.viewCnt = viewCnt;
        this.createdAt = createdAt;
    }

    public static BoardPreviewResponse from(Board board) {
        return new BoardPreviewResponse(
                board.getId(),
                board.getTitle(),
                board.getMember().getNickname(),
                board.getViewCnt(),
                board.getCreatedAt()
        );
    }
}