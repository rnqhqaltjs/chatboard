package com.example.chatboard.board.application;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.board.domain.service.BoardCommandService;
import com.example.chatboard.board.infrastructure.BoardRepository;
import com.example.chatboard.board.presentation.dto.BoardCreateRequest;
import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCommandService boardCommandService;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long memberId, BoardCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Board board = boardCommandService.create(request.getTitle(), request.getContent(), request.isAnonymous(), member);
        boardRepository.save(board);

        return board.getId();
    }
}
