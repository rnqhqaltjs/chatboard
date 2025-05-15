package com.example.chatboard.comment.application;

import com.example.chatboard.board.domain.model.Board;
import com.example.chatboard.board.infrastructure.BoardRepository;
import com.example.chatboard.comment.domain.model.Comment;
import com.example.chatboard.comment.infrastructure.CommentRepository;
import com.example.chatboard.comment.presentation.dto.request.CommentCreateRequest;
import com.example.chatboard.comment.presentation.dto.request.CommentUpdateRequest;
import com.example.chatboard.comment.presentation.dto.response.CommentPageResponse;
import com.example.chatboard.comment.presentation.exception.CommentBoardMismatchException;
import com.example.chatboard.comment.presentation.exception.CommentNotOwnedException;
import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.chatboard.board.application.BoardServiceHelper.validateExistBoard;
import static com.example.chatboard.comment.application.CommentServiceHelper.validateExistCommentWithBoardAndMember;
import static com.example.chatboard.member.application.MemberServiceHelper.validateExistMember;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long memberId, Long boardId, CommentCreateRequest request) {
        Member member = validateExistMember(memberRepository, memberId);
        Board board = validateExistBoard(boardRepository, boardId);

        Comment comment = new Comment(member, board, request.getContent());
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional(readOnly = true)
    public CommentPageResponse findAll(Long boardId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByBoardId(boardId, pageable);

        return CommentPageResponse.from(comments);
    }

    @Transactional
    public void update(Long memberId, Long boardId, Long commentId, CommentUpdateRequest request) {
        Comment comment = validateExistCommentWithBoardAndMember(commentRepository, commentId);

        if (!comment.getBoard().getId().equals(boardId)) {
            throw new CommentBoardMismatchException();
        }

        if (!comment.getMember().getId().equals(memberId)) {
            throw new CommentNotOwnedException();
        }

        comment.updateContent(request.getContent());
    }

    @Transactional
    public void delete(Long memberId, Long boardId, Long commentId) {
        Comment comment = validateExistCommentWithBoardAndMember(commentRepository, commentId);

        if (!comment.getBoard().getId().equals(boardId)) {
            throw new CommentBoardMismatchException();
        }

        if (!comment.getMember().getId().equals(memberId)) {
            throw new CommentNotOwnedException();
        }

        commentRepository.delete(comment);
    }
}
