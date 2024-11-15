package com.dk.bookmyshow.services;

import com.dk.bookmyshow.exceptions.SeatNotAvailableException;
import com.dk.bookmyshow.models.*;
import com.dk.bookmyshow.repositories.CustomerRepository;
import com.dk.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookTicketService {


    private final CustomerRepository customerRepository;
    private final ShowSeatRepository showSeatRepository;

    public BookTicketService(CustomerRepository customerRepository, ShowSeatRepository showSeatRepository) {
        this.customerRepository = customerRepository;
        this.showSeatRepository = showSeatRepository;
    }


    synchronized Booking bookTicket(Integer id, List<Integer> showSeatIds) throws Exception {

        /*
        1. check if customer is present
        2. check if all seats are available
        3. mark all seats blocked and update time of block
        4. Intialize the payment gateway
        5. return the Booking instance with isPaymentDone = False
        6. Once the payment is successful for the Booking, mark ispaymntDone = true
        7. Mark all seats to Booked.
         */

        Customer customer = customerRepository.findById(id).orElseThrow(()-> new UserPrincipalNotFoundException("Customer not found"));

        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat: showSeatList) {
            if(!showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                throw new SeatNotAvailableException("Seat is not Available");
            }
        }

        // Block all the seats and mark blockedAt time
        for(ShowSeat showSeat: showSeatList) {
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            showSeat.setBlockedAt(LocalDateTime.now());

            showSeatRepository.save(showSeat);
        }

        AmountCalculationService amountCalculationService = new AmountCalculationService();

        double amount = amountCalculationService.calculateAmount(showSeatList);

        Booking booking = new Booking();
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookedBy(customer);
        booking.setAmount(amount);
        booking.setShowSeats(showSeatList);
        booking.setSeatCount(showSeatList.size());
        booking.setBookingStatus(BookingStatus.PENDING);

        return booking;


    }

}
