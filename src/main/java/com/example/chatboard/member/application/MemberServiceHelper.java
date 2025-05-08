package com.example.chatboard.member.application;

import com.example.chatboard.member.infrastructure.MemberRepository;
import com.example.chatboard.member.presentation.exception.DuplicateMemberException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
