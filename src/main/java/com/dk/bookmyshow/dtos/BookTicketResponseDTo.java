package com.dk.bookmyshow.dtos;

import com.dk.bookmyshow.models.Booking;
import lombok.Data;

@Data
public class BookTicketResponseDTo {
    private Booking booking;
    private ResponseStatus status;
}
