package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.annotation.Inherited;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Customer extends User{
    @OneToMany(mappedBy = "bookedBy")
    List<Booking> bookings;
}
