package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@NoArgsConstructor
public class Movie extends Item{

    private String director;

    private String actor;

    @Builder
    public Movie(String director, String actor) {
        this.director = director;
        this.actor = actor;
    }
}
