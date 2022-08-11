package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "B")
@NoArgsConstructor
public class Book extends Item{

    private String author;

    private String isbn;

    @Builder
    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
