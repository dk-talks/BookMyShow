package com.dk.bookmyshow.repositories;

import com.dk.bookmyshow.models.Screen;
import com.dk.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findAllByScreen(Screen screen);
}
