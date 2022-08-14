package com.example.spring_jpa_miniproject.controller.dto;

import com.example.spring_jpa_miniproject.domain.Address;
import lombok.Getter;

import javax.persistence.Embedded;

@Getter
public class MemberDto {

    private String name;

    @Embedded
    private Address address;

    public MemberDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
