package com.codueon.boostuppractice.domain.member.service;

import com.codueon.boostuppractice.domain.member.dto.MemberDto;
import com.codueon.boostuppractice.domain.member.entity.AccountStatus;
import com.codueon.boostuppractice.domain.member.entity.Member;
import com.codueon.boostuppractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;

    public void joinMember(MemberDto.Join joinMember) {
        // TODO : 닉네임 중복, 이메일 중복 확인

        // TODO : authorityUtils 구현
        List<String> roles = List.of("USER", "ADMIN");

        Member newMember = Member.builder()
                .email(joinMember.getEmail())
                .nickname(joinMember.getNickname())
                .password(joinMember.getPassword()) // TODO : password 인코딩 구현
                .accountStatus(AccountStatus.COMMON_MEMBER)
                .roles(roles)
                .build();

        // TODO : MemberImage 로직 구현
        memberRepository.save(newMember);
    }
}
