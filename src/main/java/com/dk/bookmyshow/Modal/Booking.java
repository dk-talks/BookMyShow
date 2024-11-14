package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Booking extends BaseModal {
    @ManyToOne
    private Customer bookedBy;
    @OneToMany
    private List<ShowSeat> showSeats;
    private double amount;
    private int seatCount;
    private Date bookingTime;
    private Boolean isPaymentSuccessful;
}
