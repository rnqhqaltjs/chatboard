package com.example.chatboard.board.application;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.board.infrastructure.BoardRepository;
import com.example.chatboard.board.presentation.exception.BoardNotFoundException;
import com.example.chatboard.board.presentation.exception.BoardNotOwnedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceHelper {
    /**
     * 존재하는 게시글인지 확인 (단건 조회용)
     */
    public static Board validateExistBoard(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }

    /**
     * 게시글 ID와 회원 ID가 일치하는지 확인
     */
    public static Board validateBoardOwner(BoardRepository boardRepository, Long boardId, Long memberId) {
        return boardRepository.findByIdAndMemberId(boardId, memberId)
                .orElseThrow(BoardNotOwnedException::new);
    }
}
