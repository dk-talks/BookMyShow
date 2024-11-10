package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Theatre extends BaseModal {
    private String name;
    @ManyToOne
    private City city;
    private String address;
    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;
}