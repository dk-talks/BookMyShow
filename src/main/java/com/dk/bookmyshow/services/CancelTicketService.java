package com.dk.bookmyshow.services;

import com.dk.bookmyshow.exceptions.ShowAlreadyStartedException;
import com.dk.bookmyshow.models.Booking;
import com.dk.bookmyshow.models.BookingStatus;
import com.dk.bookmyshow.models.SeatStatus;
import com.dk.bookmyshow.models.ShowSeat;
import com.dk.bookmyshow.repositories.BookingRepository;
import com.dk.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CancelTicketService {

    private BookingRepository bookingRepository;
    private ShowSeatRepository showSeatRepository;

    public CancelTicketService(BookingRepository bookingRepository, ShowSeatRepository showSeatRepository) {
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public void cancelTicket(int bookingId) throws RuntimeException, ShowAlreadyStartedException {
        /*
        1. check if booking exists
        2. check if show start time is in future then current time
        3. change status to canceled
         */

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking is not present"));

        List<ShowSeat> bookingShowSeats = booking.getShowSeats();
        if(bookingShowSeats.get(0).getShow().getStartTime().isBefore(LocalDateTime.now())) {
            throw new ShowAlreadyStartedException("Show already started, cannot cancel");
        }

        // Free up all the ShowSeats

        for(ShowSeat showSeat: bookingShowSeats) {
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        }

        showSeatRepository.saveAll(bookingShowSeats);

        // update the booking status

        booking.setBookingStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);

    }

}
