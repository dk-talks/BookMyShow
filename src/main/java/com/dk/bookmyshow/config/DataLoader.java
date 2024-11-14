package com.dk.bookmyshow.config;

import com.dk.bookmyshow.Modal.*;
import com.dk.bookmyshow.repositories.CustomerRepository;
import com.dk.bookmyshow.repositories.MovieRepository;
import com.dk.bookmyshow.repositories.ShowSeatRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private ShowSeatRepository showSeatRepository;
    private MovieRepository movieRepository;

    //constructor injection
    public DataLoader(
            CustomerRepository customerRepository,
            ShowSeatRepository showSeatRepository,
            MovieRepository movieRepository
    ) {
        this.customerRepository = customerRepository;
        this.showSeatRepository = showSeatRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // fill dummy data into database

        // 1. Customer

        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setPassword("password123");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setPassword("password456");

        Customer customer3 = new Customer();
        customer3.setName("Charlie");
        customer3.setEmail("charlie@example.com");
        customer3.setPassword("password789");

        Customer customer4 = new Customer();
        customer4.setName("David");
        customer4.setEmail("david@example.com");
        customer4.setPassword("password101");

        Customer customer5 = new Customer();
        customer5.setName("Eve");
        customer5.setEmail("eve@example.com");
        customer5.setPassword("password202");

        customerRepository.saveAll(new ArrayList<>(Arrays.asList(customer4, customer1, customer5, customer3, customer2)));

        // 2. Movie

        Movie m1 = new Movie();
        m1.setName("Kalki AD2998");
        m1.setReleaseDate(LocalDate.of(2024, 8, 4));

        Movie m2 = new Movie();
        m2.setName("Hera pheri 3");
        m2.setReleaseDate(LocalDate.of(2024, 11, 20));

        movieRepository.saveAll(new ArrayList<>(Arrays.asList(m1, m2)));

        /*
        City -> Theatre -> Agent -> Screen -> Seat -> Show -> ShowSeats & Movie -> Booking
         */

        City city1 = new City();
        city1.setName("Jaipur");

        City city2 = new City();
        city2.setName("Kotputli");

        // Theatre

        Theatre t1 = new Theatre();
        t1.setName("Gold Cinema");
        t1.setAddress("Bansur Road, Kotputli");
        t1.setCity(city2);

        Theatre t2 = new Theatre();
        t2.setCity(city2);
        t2.setAddress("Near Deewan Hotel, Kotputli");
        t2.setName("Heeramoti Cinema");

        // Gold cinema agent
        Agent goldAgent = new Agent();
        goldAgent.setTheatre(t1);
        goldAgent.setEmail("vikas@gmail.com");
        goldAgent.setPassword("pass");
        goldAgent.setName("vikas");

        // Heeramoti Cinema Agent
        Agent heeraAgent = new Agent();
        heeraAgent.setName("Lucky");
        heeraAgent.setTheatre(t2);
        heeraAgent.setEmail("lucky@heera.in");
        heeraAgent.setEmail("HeeraPassword");

        // Gold seats and screens
        List<Seat> gold_2d_seats = new ArrayList<>();

        Screen screen1 = new Screen();

        SeatType goldSeatType = new SeatType();
        goldSeatType.setSeatType("Gold Basic");
        for(int i = 0; i<10; i++) {
            Seat seat = new Seat();
            seat.setScreen(screen1);
            seat.setSeatRow(0);
            seat.setSeatCol(i);
            seat.setSeatNum("A" + i);
            seat.setSeatType(goldSeatType);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            gold_2d_seats.add(seat);
        }

        screen1.setName("A1_2D");
        screen1.setFeatures(new ArrayList<>(List.of(Feature.TWO_D)));
        screen1.setTheatre(t1);
        screen1.setSeats(gold_2d_seats);

        //persist this screen

        // Heeramoti seats and screen

        List<Seat> heera_3d_seats = new ArrayList<>();

        Screen screen2 = new Screen();

        SeatType heeraBasicSeatType = new SeatType();
        heeraBasicSeatType.setSeatType("Heera Basic");
        SeatType heeraPremiuSeatType = new SeatType();
        heeraPremiuSeatType.setSeatType("Heera Premium");

        for(int i = 0; i<20; i++) {
            Seat seat = new Seat();
            seat.setScreen(screen2);
            seat.setSeatRow((i > 9) ? 1 : 0);
            seat.setSeatCol((i > 9) ? i-10 : i);
            seat.setSeatNum("H" + i);
            seat.setSeatType((i > 9) ? heeraPremiuSeatType : heeraBasicSeatType);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            heera_3d_seats.add(seat);
        }

        screen2.setName("H1_2D");
        screen2.setFeatures(new ArrayList<>(Arrays.asList(Feature.THREE_D, Feature.ATMOS)));
        screen2.setTheatre(t2);
        screen2.setSeats(heera_3d_seats);

        // create the shows

        List<ShowSeat> show1Seats = new ArrayList<>();

        Show show1 = new Show();

        // fetch all list from seat database for screen2
        // and assign them to show1Seats



        show1.setMovie(m1);
        show1.setScreen(screen2);
//        show1.set







    }
}
