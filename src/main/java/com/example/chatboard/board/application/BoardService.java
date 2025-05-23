package com.example.chatboard.board.application;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.board.infrastructure.BoardRepository;
import com.example.chatboard.board.presentation.dto.request.BoardCreateRequest;
import com.example.chatboard.board.presentation.dto.request.BoardUpdateRequest;
import com.example.chatboard.board.presentation.dto.response.BoardPageResponse;
import com.example.chatboard.board.presentation.dto.response.BoardResponse;
import com.example.chatboard.board.presentation.exception.BoardNotOwnedException;
import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.chatboard.board.application.BoardServiceHelper.validateExistBoard;
import static com.example.chatboard.board.application.BoardServiceHelper.validateExistBoardWithMember;
import static com.example.chatboard.member.application.MemberServiceHelper.validateExistMember;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long memberId, BoardCreateRequest request) {
        Member member = validateExistMember(memberRepository, memberId);

        Board board = new Board(request.getTitle(), request.getContent(), member);
        boardRepository.save(board);

        return board.getId();
    }

    @Transactional
    public BoardResponse findById(Long boardId) {
        Board board = validateExistBoard(boardRepository, boardId);

        board.increaseViewCount();

        return BoardResponse.from(board);
    }

    @Transactional(readOnly = true)
    public BoardPageResponse findAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return BoardPageResponse.from(boards);
    }

    @Transactional
    public void update(Long memberId, Long boardId, BoardUpdateRequest request) {
        Board board = validateExistBoardWithMember(boardRepository, boardId);

        if (!board.getMember().getId().equals(memberId)) {
            throw new BoardNotOwnedException();
        }

        board.updateContent(request.getTitle(), request.getContent());
    }

    @Transactional
    public void delete(Long memberId, Long boardId) {
        Board board = validateExistBoardWithMember(boardRepository, boardId);

        if (!board.getMember().getId().equals(memberId)) {
            throw new BoardNotOwnedException();
        }

        boardRepository.delete(board);
    }
}