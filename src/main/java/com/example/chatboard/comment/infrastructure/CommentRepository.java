package com.example.chatboard.comment.infrastructure;

import com.example.chatboard.comment.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByBoardId(Long boardId, Pageable pageable);

    @Query("SELECT c FROM Comment c JOIN FETCH c.board b JOIN FETCH c.member m WHERE c.id = :commentId")
    Optional<Comment> findWithBoardAndMemberById(@Param("commentId") Long commentId);
}
