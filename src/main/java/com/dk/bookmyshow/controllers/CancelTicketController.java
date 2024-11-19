package com.dk.bookmyshow.controllers;

import com.dk.bookmyshow.dtos.CancelTicketRequestDTO;
import com.dk.bookmyshow.dtos.CancelTicketResponseDTO;
import com.dk.bookmyshow.dtos.ResponseStatus;
import com.dk.bookmyshow.repositories.BookingRepository;
import com.dk.bookmyshow.services.CancelTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CancelTicketController {


    private final CancelTicketService cancelTicketService;

    public CancelTicketController(CancelTicketService cancelTicketService) {
        this.cancelTicketService = cancelTicketService;
    }

    public CancelTicketResponseDTO cancelTicket(CancelTicketRequestDTO requestDTO) {

        CancelTicketResponseDTO responseDTO = new CancelTicketResponseDTO();

        try {
            cancelTicketService.cancelTicket(requestDTO.getBookingId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }
}
