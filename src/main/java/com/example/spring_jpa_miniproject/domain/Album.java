package com.example.spring_jpa_miniproject.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@NoArgsConstructor
public class Album extends Item{

    private String artist;

    private String etc;

    @Builder
    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}
