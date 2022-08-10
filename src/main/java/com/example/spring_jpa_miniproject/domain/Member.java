package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Orders> orderList = new ArrayList<>();

    @Builder
    public Member(String name, Address address, List<Orders> orderList) {
        this.name = name;
        this.address = address;
        this.orderList = orderList;
    }
}
