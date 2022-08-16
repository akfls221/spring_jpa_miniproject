package com.example.spring_jpa_miniproject.controller.dto;

import com.example.spring_jpa_miniproject.domain.Address;
import lombok.Getter;

@Getter
public class MemberDto {

    private String name;

    private Address address;

    public MemberDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
