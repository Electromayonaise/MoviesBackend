package icesi.movies.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.model.Customer;
import icesi.movies.backend.model.Showtime;
import icesi.movies.backend.repositories.ReservationRepository;
import icesi.movies.backend.repositories.CustomerRepository;
import icesi.movies.backend.repositories.ShowtimeRepository;
import icesi.movies.backend.services.interfaces.ReservationService;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Reservation createReservation(Long customerId, Long showtimeId, int seatCount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setShowtime(showtime);
        reservation.setSeatCount(seatCount);

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public void cancelUserReservation(Long id, Long customerId) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getCustomerId().equals(customerId)) {
            throw new RuntimeException("Unauthorized operation");
        }

        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getUserReservations(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return reservationRepository.findByCustomer(customer);
    }


}
