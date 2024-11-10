package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class ShowSeat extends BaseModal {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated
    @ManyToOne
    private ShowSeatType showSeatType;
}
