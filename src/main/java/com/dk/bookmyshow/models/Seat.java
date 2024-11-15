package com.dk.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "seats")
public class Seat extends BaseModal{
    private String seatNum;
    @ManyToOne
    private Screen screen;
    private int seatRow;
    private int seatCol;
    @ManyToOne
    private SeatType seatType;
    private SeatStatus seatStatus;
}
