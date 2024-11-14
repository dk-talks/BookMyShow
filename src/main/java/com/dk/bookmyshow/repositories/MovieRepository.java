package com.dk.bookmyshow.repositories;

import com.dk.bookmyshow.Modal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
