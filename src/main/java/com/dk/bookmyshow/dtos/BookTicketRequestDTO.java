package com.dk.bookmyshow.dtos;

import com.dk.bookmyshow.Modal.Customer;
import com.dk.bookmyshow.Modal.ShowSeat;

import java.util.List;

public class BookTicketRequestDTO {
    private Integer customerId;
    private List<Integer> showSeatIds;
    private ResponseStatus status;
}
