package com.dk.bookmyshow.repositories;

import com.dk.bookmyshow.Modal.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
