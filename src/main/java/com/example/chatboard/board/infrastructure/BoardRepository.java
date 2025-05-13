package com.example.chatboard.board.infrastructure;

import com.example.chatboard.board.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndMemberId(Long boardId, Long memberId);
}
