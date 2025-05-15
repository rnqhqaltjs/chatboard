package com.example.chatboard.comment.presentation.dto.response;

import com.example.chatboard.comment.domain.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {

    private Long id;
    private String content;
    private String nickName;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String nickName, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.nickName = nickName;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getMember().getNickname(),
                comment.getCreatedAt()
        );
    }
}