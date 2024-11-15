package com.dk.bookmyshow.dtos;

import java.util.List;

public class BookTicketRequestDTO {
    private Integer customerId;
    private List<Integer> showSeatIds;
    private ResponseStatus status;
}
