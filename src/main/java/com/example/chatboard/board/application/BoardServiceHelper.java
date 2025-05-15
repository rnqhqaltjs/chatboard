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
     * 게시글 존재 여부 확인 (단순 조회)
     */
    public static Board validateExistBoard(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }

    /**
     * 게시글 존재 여부 확인 (회원 즉시 로딩)
     */
    public static Board validateExistBoardWithMember(BoardRepository boardRepository, Long boardId) {
        return boardRepository.findWithMemberById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }
}