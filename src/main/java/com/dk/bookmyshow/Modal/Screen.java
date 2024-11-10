package com.dk.bookmyshow.Modal;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Screen extends BaseModal {
    private String name;
    @ManyToOne
    private Theatre theatre;
    @ElementCollection
    private List<Feature> features;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
}
