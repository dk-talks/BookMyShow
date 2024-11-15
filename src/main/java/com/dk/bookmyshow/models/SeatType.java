package com.dk.bookmyshow.models;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SeatType extends BaseModal{
    private String seatType;
}
