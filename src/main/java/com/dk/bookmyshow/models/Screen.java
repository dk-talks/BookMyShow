package com.dk.bookmyshow.models;

import jakarta.persistence.*;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Screen extends BaseModal {
    private String name;
    @ManyToOne
    private Theatre theatre;
    @ElementCollection
    private List<Feature> features;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Seat> seats;
}
