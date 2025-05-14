package com.example.chatboard.board.presentation.dto.response;

import com.example.chatboard.board.domain.model.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardPageResponse {

    private boolean hasNext;
    private int totalPages;
    private long totalElements;
    private List<BoardPreviewResponse> boards;

    public BoardPageResponse(boolean hasNext, int totalPages, long totalElements, List<BoardPreviewResponse> boards) {
        this.hasNext = hasNext;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.boards = boards;
    }

    public static BoardPageResponse from(Page<Board> page) {
        List<BoardPreviewResponse> boards = page.getContent().stream()
                .map(BoardPreviewResponse::from)
                .collect(Collectors.toList());

        return new BoardPageResponse(
                page.hasNext(),
                page.getTotalPages(),
                page.getTotalElements(),
                boards);
    }
}