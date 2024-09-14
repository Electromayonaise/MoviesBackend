package icesi.movies.backend.services;

import icesi.movies.backend.model.Customer;
import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.model.Showtime;
import icesi.movies.backend.repositories.CustomerRepository;
import icesi.movies.backend.repositories.ReservationRepository;
import icesi.movies.backend.repositories.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import icesi.movies.backend.services.impl.ReservationServiceImpl;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationServiceImplTests {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ShowtimeRepository showtimeRepository;

    private Customer customer;
    private Showtime showtime;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);

        showtime = new Showtime();
        showtime.setId(1L);
    }

    @Test
    public void createReservation_ShouldReturnReservation() {
        Long customerId = 1L;
        Long showtimeId = 1L;
        int seatCount = 3;

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setSeatCount(seatCount);
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createReservation(customerId, showtimeId, seatCount);

        assertThat(result).isNotNull();
        assertThat(result.getSeatCount()).isEqualTo(seatCount);
    }

    @Test
    public void cancelReservation_ShouldCallDeleteById() {
        Long reservationId = 1L;

        reservationService.cancelReservation(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    public void getUserReservations_ShouldReturnListOfReservations() {
        Long customerId = 1L;

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setSeatCount(3);
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(reservationRepository.findByCustomer(customer)).thenReturn(List.of(reservation));

        List<Reservation> result = reservationService.getUserReservations(customerId);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getSeatCount()).isEqualTo(3);
    }

    @Test
    public void getAllReservations_ShouldReturnListOfReservations() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setSeatCount(3);
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);

        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        List<Reservation> result = reservationService.getAllReservations();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getSeatCount()).isEqualTo(3);
    }
}
