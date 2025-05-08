package com.example.chatboard.board.domain.service;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.member.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommandService {

    public Board create(String title, String content, boolean isAnonymous, Member member) {
        return new Board(title, content, isAnonymous, member);
    }
}
