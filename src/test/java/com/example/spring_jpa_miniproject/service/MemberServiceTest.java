package com.example.spring_jpa_miniproject.service;

import com.example.spring_jpa_miniproject.controller.dto.MemberDto;
import com.example.spring_jpa_miniproject.domain.Address;
import com.example.spring_jpa_miniproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("성공경우")
    void 회원가입() {

        //Given
        Address address = new Address("서울", "청룡13길", "20-3");
        MemberDto memberDto = new MemberDto("Test", address);

        //when
        Long id = memberService.join(memberDto);

        //then
        assertThat(memberDto.getName()).isEqualTo(memberRepository.findOne(id).get().getName());
    }

    @Test
    @DisplayName("실패경우")
    void 회원가입_실패() {

        //Given
        Address address = new Address("서울", "청룡13길", "20-3");
        MemberDto memberDto = new MemberDto("Test", address);

        //when
        memberService.join(memberDto);

        //then
        MemberDto memberDto2 = new MemberDto("Test", address);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> memberService.join(memberDto2));
    }

}