package icesi.movies.backend.repositories;

import icesi.movies.backend.model.Customer;
import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.model.Showtime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    private Customer customer;
    private Showtime showtime;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setFirstName("John Doe");
        customerRepository.save(customer);

        showtime = new Showtime();
        showtime.setStartTime(LocalTime.of(19, 0)); // Set start time to 19:00 (7 PM)
        showtimeRepository.save(showtime);
    }

    @Test
    void testSaveAndFindById() {
        Reservation reservation = new Reservation();
        reservation.setSeatCount(4);
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);

        Reservation savedReservation = reservationRepository.save(reservation);

        Optional<Reservation> foundReservation = reservationRepository.findById(savedReservation.getId());

        assertTrue(foundReservation.isPresent());
        assertEquals(savedReservation.getId(), foundReservation.get().getId());
        assertEquals(4, foundReservation.get().getSeatCount());
        assertEquals(customer.getId(), foundReservation.get().getCustomer().getId());
        assertEquals(showtime.getId(), foundReservation.get().getShowtime().getId());
    }

    @Test
    void testDelete() {
        Reservation reservation = new Reservation();
        reservation.setSeatCount(2);
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);

        Reservation savedReservation = reservationRepository.save(reservation);
        reservationRepository.deleteById(savedReservation.getId());

        Optional<Reservation> foundReservation = reservationRepository.findById(savedReservation.getId());

        assertTrue(foundReservation.isEmpty());
    }

    @Test
    void testFindByCustomer() {
        Reservation reservation1 = new Reservation();
        reservation1.setSeatCount(3);
        reservation1.setCustomer(customer);
        reservation1.setShowtime(showtime);
        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setSeatCount(2);
        reservation2.setCustomer(customer);
        reservation2.setShowtime(showtime);
        reservationRepository.save(reservation2);

        List<Reservation> reservations = reservationRepository.findByCustomer(customer);

        assertEquals(2, reservations.size());
    }
}
