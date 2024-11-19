package com.dk.bookmyshow.config;

import com.dk.bookmyshow.controllers.BookTicketController;
import com.dk.bookmyshow.dtos.BookTicketRequestDTO;
import com.dk.bookmyshow.dtos.BookTicketResponseDTo;
import com.dk.bookmyshow.dtos.ResponseStatus;
import com.dk.bookmyshow.models.*;
import com.dk.bookmyshow.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ShowSeatTypeRepository showSeatTypeRepository;
    private CustomerRepository customerRepository;
    private ShowSeatRepository showSeatRepository;
    private MovieRepository movieRepository;
    private AgentRepository agentRepository;
    private BookingRepository bookingRepository;
    private CityRepository cityRepository;
    private PaymentRepository paymentRepository;
    private ScreenRepository screenRepository;
    private SeatRepository seatRepository;
    private SeatTypeRepository seatTypeRepository;
    private ShowRepository showRepository;
    private TheatreRepository theatreRepository;
    private UserRepository userRepository;
    private BookTicketController bookTicketController;



    //constructor injection
    public DataLoader(
            CustomerRepository customerRepository,
            ShowSeatRepository showSeatRepository,
            MovieRepository movieRepository,
            AgentRepository agentRepository,
            BookingRepository bookingRepository,
            CityRepository cityRepository,
            PaymentRepository paymentRepository,
            ScreenRepository screenRepository,
            SeatRepository seatRepository,
            SeatTypeRepository seatTypeRepository,
            ShowRepository showRepository,
            TheatreRepository theatreRepository,
            UserRepository userRepository,
            ShowSeatTypeRepository showSeatTypeRepository,
            BookTicketController bookTicketController) {
        this.customerRepository = customerRepository;
        this.showSeatRepository = showSeatRepository;
        this.movieRepository = movieRepository;
        this.agentRepository = agentRepository;
        this.bookingRepository = bookingRepository;
        this.cityRepository = cityRepository;
        this.paymentRepository = paymentRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.seatTypeRepository = seatTypeRepository;
        this.showRepository = showRepository;
        this.theatreRepository = theatreRepository;
        this.userRepository = userRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.bookTicketController = bookTicketController;
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

        cityRepository.saveAll(new ArrayList<>(Arrays.asList(city1, city2)));

        // Theatre

        Theatre t1 = new Theatre();
        t1.setName("Gold Cinema");
        t1.setAddress("Bansur Road, Kotputli");
        t1.setCity(city2);

        Theatre t2 = new Theatre();
        t2.setCity(city2);
        t2.setAddress("Near Deewan Hotel, Kotputli");
        t2.setName("Heeramoti Cinema");

        theatreRepository.saveAll(new ArrayList<>(Arrays.asList(t1, t2)));

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

        agentRepository.saveAll(new ArrayList<>(Arrays.asList(goldAgent, heeraAgent)));

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

        seatTypeRepository.saveAll(new ArrayList<>(Arrays.asList(heeraBasicSeatType, heeraPremiuSeatType, goldSeatType)));

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

        screenRepository.saveAll(new ArrayList<>(Arrays.asList(screen1, screen2)));
        // create the shows

        List<ShowSeat> show1Seats = new ArrayList<>();

        Show show1 = new Show();

        // fetch all list from seat database for screen2
        // and assign them to show1Seats

        // fetch all seats from screen 2
        List<Seat> screen2Seats = seatRepository.findAllByScreen(screen2);

        for(Seat seat: screen2Seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatStatus(seat.getSeatStatus());
            showSeat.setShow(show1);
            showSeat.setSeat(seat);
            show1Seats.add(showSeat);
        }

        show1.setMovie(m1);
        show1.setScreen(screen2);
        show1.setMovie(m1);
        show1.setShowSeats(show1Seats);
        show1.setStartTime(LocalDateTime.of(2024, 11, 20, 10, 0));
        show1.setEndTime(LocalDateTime.of(2024, 11, 20, 13, 0));

        //persist show in database
        showRepository.save(show1);


        // Show SeatType - two types of seats for show1

        //fetch all seatTypes from show1;


        ShowSeatType type1 = new ShowSeatType();
        type1.setPrice(100);
        type1.setSeatType(heeraBasicSeatType);
        type1.setShow(show1);

        ShowSeatType type2 = new ShowSeatType();
        type2.setPrice(200);
        type2.setSeatType(heeraPremiuSeatType);
        type2.setShow(show1);

        showSeatTypeRepository.saveAll(new ArrayList<>(Arrays.asList(type1, type2)));


        // book a ticket (for demo purpose)

        BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
        requestDTO.setCustomerId(customer1.getId());
        requestDTO.setShowSeatIds(new ArrayList<>(Arrays.asList(1, 15)));
        BookTicketResponseDTo responseDTo = bookTicketController.bookTicket(requestDTO);
        if(responseDTo.getStatus().equals(ResponseStatus.SUCCESS)) {
            System.out.println("Successfully booked tickets");
        } else {
            System.out.println("Issue in booking ticket");
        }





    }
}
