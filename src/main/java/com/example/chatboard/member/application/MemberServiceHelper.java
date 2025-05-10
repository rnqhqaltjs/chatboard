package com.example.chatboard.member.application;

import com.example.chatboard.member.domain.model.Member;
import com.example.chatboard.member.infrastructure.MemberRepository;
import com.example.chatboard.member.presentation.exception.DuplicateMemberException;
import com.example.chatboard.member.presentation.exception.InvalidPasswordException;
import com.example.chatboard.member.presentation.exception.NotFoundMemberException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceHelper {
    /**
     * 중복 이메일 검사
     */
    public static void validateDuplicateEmail(MemberRepository memberRepository, String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateMemberException();
        }
    }

    /**
     * 로그인 시 이메일 존재 검사
     */
    public static Member validateExistMember(MemberRepository memberRepository, String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundMemberException();
                });
    }

    /**
     * 로그인 시 비밀번호 유효성 검사
     */
    public static void validatePassword(String rawPassword, String encodedPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new InvalidPasswordException();
        }
    }
}
