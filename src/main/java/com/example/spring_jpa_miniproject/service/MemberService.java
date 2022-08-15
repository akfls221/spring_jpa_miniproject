package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.controller.dto.MemberDto;
import com.example.spring_jpa_miniproject.domain.Member;
import com.example.spring_jpa_miniproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto request) {
        if (!memberRepository.findByName(request.getName()).isEmpty()) {
            throw new IllegalArgumentException("이미 가입되어 있는 회원입니다.");
        }
        Member member = Member.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        memberRepository.save(member);

        return member.getId();
    }

    public List<MemberDto> allMember() {
        List<Member> members = memberRepository.findAll().orElseThrow(() -> new IllegalArgumentException("가입되어 있는 회원이 없습니다."));
        return members.stream()
                .map(member -> new MemberDto(member.getName(), member.getAddress()))
                .collect(Collectors.toList());
    }
}
