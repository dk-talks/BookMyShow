package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Movie extends BaseModal{
    private String name;
    private LocalDate releaseDate;
}
