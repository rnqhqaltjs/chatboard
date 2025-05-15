package com.example.chatboard.board.presentation.api;

import com.example.chatboard.board.application.BoardService;
import com.example.chatboard.board.presentation.dto.request.BoardCreateRequest;
import com.example.chatboard.board.presentation.dto.request.BoardUpdateRequest;
import com.example.chatboard.board.presentation.dto.response.BoardPageResponse;
import com.example.chatboard.board.presentation.dto.response.BoardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal(expression = "id") Long memberId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        return new ResponseEntity<>(boardService.create(memberId, request), HttpStatus.CREATED);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> findById(@PathVariable Long boardId) {
        return new ResponseEntity<>(boardService.findById(boardId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BoardPageResponse> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(boardService.findAll(pageable), HttpStatus.OK);
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
