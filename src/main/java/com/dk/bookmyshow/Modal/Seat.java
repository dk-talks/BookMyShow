package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity(name = "seats")
public class Seat extends BaseModal{
    private String seatNum;
    @ManyToOne
    private Screen screen;
    private int seatRow;
    private int seatCol;
    @ManyToOne
    private SeatType seatType;
}
