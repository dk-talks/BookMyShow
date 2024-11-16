package com.dk.bookmyshow.controllers;

import com.dk.bookmyshow.dtos.BookTicketRequestDTO;
import com.dk.bookmyshow.dtos.BookTicketResponseDTo;
import com.dk.bookmyshow.dtos.ResponseStatus;
import com.dk.bookmyshow.models.Booking;
import com.dk.bookmyshow.models.BookingStatus;
import com.dk.bookmyshow.services.BookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookTicketController {

    @Autowired
    private BookTicketService bookTicketService;

    public BookTicketResponseDTo bookTicket(BookTicketRequestDTO bookTicketRequestDTO) {

        BookTicketResponseDTo responseDTo = new BookTicketResponseDTo();

        try {
            Booking booking = bookTicketService.bookTicket(bookTicketRequestDTO.getCustomerId(), bookTicketRequestDTO.getShowSeatIds());
            responseDTo.setBooking(booking);
            responseDTo.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTo.setStatus(ResponseStatus.FAILURE);
            throw new RuntimeException(e);
        }

        return responseDTo;
    }

}
