package com.example.chatboard.board.presentation.api;

import com.example.chatboard.board.application.BoardService;
import com.example.chatboard.board.presentation.dto.BoardCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal(expression = "id") Long memberId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        Long boardId = boardService.create(memberId, request);

        return new ResponseEntity<>(boardId, HttpStatus.CREATED);
    }
}
