package com.dk.bookmyshow.services;

import com.dk.bookmyshow.Modal.Booking;
import com.dk.bookmyshow.Modal.Customer;
import com.dk.bookmyshow.Modal.ShowSeat;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookTicketService {



    Booking bookTicket(Integer id, List<Integer> showSeatIds) {

        /*
        1. check if customer is present
        2. check if all seats are available
        3. mark all seats blocked and update time of block
        4. Intialize the payment gateway
        5. return the Booking instance with isPaymentDone = False
        6. Once the payment is successful for the Booking, mark ispaymntDone = true
        7. Mark all seats to Booked.
         */


        return null;
    }

}
