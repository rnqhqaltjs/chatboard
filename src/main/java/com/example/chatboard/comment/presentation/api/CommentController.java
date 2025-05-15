package com.example.chatboard.comment.presentation.api;

import com.example.chatboard.comment.application.CommentService;
import com.example.chatboard.comment.presentation.dto.request.CommentCreateRequest;
import com.example.chatboard.comment.presentation.dto.response.CommentPageResponse;
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
@RequestMapping("/api/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal(expression = "id") Long memberId,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        return new ResponseEntity<>(commentService.create(memberId, boardId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommentPageResponse> findAll(
            @PathVariable Long boardId,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(commentService.findAll(boardId, pageable), HttpStatus.OK);
    }
}