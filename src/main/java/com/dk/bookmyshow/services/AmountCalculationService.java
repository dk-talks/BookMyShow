package com.dk.bookmyshow.services;

import com.dk.bookmyshow.models.Show;
import com.dk.bookmyshow.models.ShowSeat;
import com.dk.bookmyshow.models.ShowSeatType;
import com.dk.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountCalculationService {

    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;


    public double calculateAmount(List<ShowSeat> showSeats) {
        double amount = 0;

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(showSeats.get(0).getShow());

        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }
        return amount;
    }

}
