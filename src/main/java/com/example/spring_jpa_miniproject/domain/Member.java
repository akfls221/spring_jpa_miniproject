package com.example.spring_jpa_miniproject.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();
}
