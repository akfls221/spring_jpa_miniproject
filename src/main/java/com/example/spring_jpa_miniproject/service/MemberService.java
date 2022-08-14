package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.controller.dto.MemberDto;
import com.example.spring_jpa_miniproject.domain.Member;
import com.example.spring_jpa_miniproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto request) {
        if (memberRepository.findByName(request.getName())) {
            throw new IllegalArgumentException("이미 가입되어 있는 회원입니다.");
        }
        Member member = Member.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        memberRepository.save(member);

        return member.getId();
    }
}
