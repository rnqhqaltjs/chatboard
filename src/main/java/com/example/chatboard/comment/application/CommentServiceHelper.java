package com.example.chatboard.comment.application;

import com.example.chatboard.comment.domain.model.Comment;
import com.example.chatboard.comment.infrastructure.CommentRepository;
import com.example.chatboard.comment.presentation.exception.CommentNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentServiceHelper {
    /**
     * 댓글 존재 여부 확인 (게시물 및 회원 즉시 로딩)
     */
    public static Comment validateExistCommentWithBoardAndMember(CommentRepository commentRepository, Long commentId) {
        return commentRepository.findWithBoardAndMemberById(commentId)
                .orElseThrow(CommentNotFoundException::new);
    }
}
