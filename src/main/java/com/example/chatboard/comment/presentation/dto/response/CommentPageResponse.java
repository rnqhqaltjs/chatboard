package com.example.chatboard.comment.presentation.dto.response;

import com.example.chatboard.comment.domain.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPageResponse {

    private boolean hasNext;
    private List<CommentResponse> comments;

    public CommentPageResponse(boolean hasNext, List<CommentResponse> comments) {
        this.hasNext = hasNext;
        this.comments = comments;
    }

    public static CommentPageResponse from(Slice<Comment> page) {
        List<CommentResponse> comments = page.getContent().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());

        return new CommentPageResponse(page.hasNext(), comments);
    }
}
