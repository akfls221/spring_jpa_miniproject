package com.example.spring_jpa_miniproject.domain;

public enum OrderStatus {
    ORDER("주문"), CANCEL("취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
