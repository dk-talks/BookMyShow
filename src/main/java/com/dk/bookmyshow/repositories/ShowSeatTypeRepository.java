package com.dk.bookmyshow.repositories;

import com.dk.bookmyshow.models.Show;
import com.dk.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Integer> {
    List<ShowSeatType> findAllByShow(Show show);
}
