package com.dk.bookmyshow.services;

import com.dk.bookmyshow.exceptions.SeatNotAvailableException;
import com.dk.bookmyshow.models.*;
import com.dk.bookmyshow.repositories.BookingRepository;
import com.dk.bookmyshow.repositories.CustomerRepository;
import com.dk.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookTicketService {


    private final CustomerRepository customerRepository;
    private final ShowSeatRepository showSeatRepository;
    private final AmountCalculationService amountCalculationService;
    private final BookingRepository bookingRepository;

    public BookTicketService(CustomerRepository customerRepository, ShowSeatRepository showSeatRepository, AmountCalculationService amountCalculationService, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.showSeatRepository = showSeatRepository;
        this.amountCalculationService = amountCalculationService;
        this.bookingRepository = bookingRepository;
    }

    public Booking bookTicket(Integer id, List<Integer> showSeatIds) throws Exception {

        /*
        1. check if customer is present
        2. check if all seats are available
        3. mark all seats blocked and update time of block
        4. Intialize the payment gateway
        5. return the Booking instance with isPaymentDone = False
        6. Once the payment is successful for the Booking, mark ispaymntDone = true
        7. Mark all seats to Booked.
         */
        if(showSeatIds.isEmpty()) {
            throw new RuntimeException("Not seat is selected to book");
        }
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new UserPrincipalNotFoundException("Customer not found"));

        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);

        if(showSeatIds.size() != showSeatList.size()) {
            throw new RuntimeException("Some Show Seats Ids are invalid and not present in database");
        }

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

        double amount = amountCalculationService.calculateAmount(showSeatList);

        Booking booking = new Booking();
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookedBy(customer);
        booking.setAmount(amount);
        booking.setShowSeats(showSeatList);
        booking.setSeatCount(showSeatList.size());
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);

    }

}
