package com.example.chatboard.member.application;

import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.domain.model.MemberRole;
import com.example.chatboard.member.presentation.dto.request.SignUpRequest;
import com.example.chatboard.member.infrastructure.MemberRepository;
import com.example.chatboard.member.presentation.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.chatboard.member.application.MemberServiceHelper.validateDuplicateEmail;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        validateDuplicateEmail(memberRepository, request.getEmail());

        Member member = new Member(
                request.getNickname(),
                request.getEmail(),
                request.getPassword(),
                MemberRole.USER);
        memberRepository.save(member);

        return new SignUpResponse(
                member.getNickname(),
                member.getEmail(),
                member.getFormattedDateAsString());
    }
}