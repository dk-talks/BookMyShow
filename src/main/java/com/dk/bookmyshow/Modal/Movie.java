package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Movie extends BaseModal{
    private String name;
    private Date releaseDate;
}
