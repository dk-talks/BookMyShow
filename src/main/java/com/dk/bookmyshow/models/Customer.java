package com.dk.bookmyshow.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Customer extends User{
    @OneToMany(mappedBy = "bookedBy", cascade = CascadeType.ALL)
    List<Booking> bookings;
}
