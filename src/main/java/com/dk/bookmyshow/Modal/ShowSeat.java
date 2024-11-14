package com.dk.bookmyshow.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ShowSeat extends BaseModal {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated
    @ManyToOne
    private ShowSeatType showSeatType;
    private SeatStatus seatStatus;
    private Date blockedAt;

}
