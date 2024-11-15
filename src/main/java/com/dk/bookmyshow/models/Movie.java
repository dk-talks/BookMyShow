package com.dk.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Movie extends BaseModal{
    private String name;
    private LocalDate releaseDate;
}
