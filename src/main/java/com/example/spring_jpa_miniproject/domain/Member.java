package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    ///연관관계 편의 메서드
    void addOrder(Orders orders) {
        this.getOrderList().add(orders);
        if (orders.getMember() != this) {
            orders.setMember(this);
        }
    }
}
