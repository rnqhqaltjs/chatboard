package com.example.chatboard.member.application;

import com.example.chatboard.global.security.JwtTokenProvider;
import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.presentation.dto.request.LoginRequest;
import com.example.chatboard.member.presentation.dto.request.SignUpRequest;
import com.example.chatboard.member.infrastructure.MemberRepository;
import com.example.chatboard.member.presentation.dto.response.LoginResponse;
import com.example.chatboard.member.presentation.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.chatboard.member.application.MemberServiceHelper.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        validateDuplicateEmail(memberRepository, request.getEmail());

        Member member = new Member(
                request.getNickname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        memberRepository.save(member);

        return new SignUpResponse(
                member.getNickname(),
                member.getEmail(),
                member.getFormattedDateAsString());
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Member member = validateExistMember(memberRepository, request.getEmail());
        validatePassword(
                request.getPassword(),
                member.getPassword(),
                passwordEncoder);

        String token = jwtTokenProvider.generateToken(member);
        return new LoginResponse(token);
    }
}