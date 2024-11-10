package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
public class Customer extends User{
    @OneToMany
    List<Booking> bookings;
}
