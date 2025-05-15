package com.example.chatboard.comment.infrastructure;

import com.example.chatboard.comment.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
