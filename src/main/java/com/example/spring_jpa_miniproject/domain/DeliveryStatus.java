package com.example.spring_jpa_miniproject.domain;

public enum DeliveryStatus {

    READY("준비"), COMP("배송");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
