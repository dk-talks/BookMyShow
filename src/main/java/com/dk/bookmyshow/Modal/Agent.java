package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "agents")
public class Agent extends User{
    @ManyToOne
    private Theatre theatre;
}
