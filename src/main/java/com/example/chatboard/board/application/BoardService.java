package com.example.chatboard.board.application;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.board.infrastructure.BoardRepository;
import com.example.chatboard.board.presentation.dto.BoardCreateRequest;
import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.chatboard.board.application.BoardServiceHelper.validateBoardOwner;
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
    public void delete(Long memberId, Long boardId) {
        Board board = validateBoardOwner(boardRepository, boardId, memberId);

        boardRepository.delete(board);
    }
}