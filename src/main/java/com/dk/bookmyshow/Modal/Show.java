package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Show extends BaseModal {
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
}
