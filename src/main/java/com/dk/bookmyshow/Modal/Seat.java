package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat extends BaseModal{
    private String seatNum;
    @ManyToOne
    private Screen screen;
    private int row;
    private int col;
    @Enumerated
    private SeatType seatType;
}
