package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment extends BaseModal{
    @ManyToOne
    private Customer payedBy;
    @ManyToOne
    private Booking booking;
    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;
    private double amount;
    private String referenceNumber;
}
