package com.dk.bookmyshow.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDTO {
    private Integer customerId;
    private List<Integer> showSeatIds;
    private ResponseStatus status;
}
