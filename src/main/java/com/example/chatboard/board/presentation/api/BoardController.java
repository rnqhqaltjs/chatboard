package com.example.chatboard.board.presentation.api;

import com.example.chatboard.board.application.BoardService;
import com.example.chatboard.board.presentation.dto.BoardCreateRequest;
import com.example.chatboard.board.presentation.dto.BoardUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{boardId}")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal(expression = "id") Long memberId,
            @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequest request
    ) {
        boardService.update(memberId, boardId, request);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal(expression = "id") Long memberId,
            @PathVariable Long boardId
    ) {
        boardService.delete(memberId, boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
