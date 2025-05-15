package com.example.chatboard.board.infrastructure;

import com.example.chatboard.board.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b " +
            "JOIN FETCH b.member m " +
            "WHERE b.id = :boardId")
    Optional<Board> findWithMemberById(@Param("boardId") Long boardId);
}
