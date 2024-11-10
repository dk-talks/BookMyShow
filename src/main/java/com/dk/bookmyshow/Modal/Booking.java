package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Booking extends BaseModal {
    @ManyToOne
    private Customer bookedBy;
    @OneToMany
    private List<ShowSeat> showSeats;
    private double amount;
    private int seatCount;
}
