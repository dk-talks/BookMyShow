package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Agent extends User{
    @ManyToMany
    private Theatre theatre;
}
