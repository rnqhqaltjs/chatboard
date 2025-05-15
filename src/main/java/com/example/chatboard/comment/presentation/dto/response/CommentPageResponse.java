package com.example.chatboard.comment.presentation.dto.response;

import com.example.chatboard.comment.domain.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPageResponse {

    private boolean hasNext;
    private int totalPages;
    private long totalElements;
    private List<CommentResponse> comments;

    public CommentPageResponse(boolean hasNext, int totalPages, long totalElements, List<CommentResponse> comments) {
        this.hasNext = hasNext;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.comments = comments;
    }

    public static CommentPageResponse from(Page<Comment> page) {
        List<CommentResponse> comments = page.getContent().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());

        return new CommentPageResponse(
                page.hasNext(),
                page.getTotalPages(),
                page.getTotalElements(),
                comments);
    }
}
