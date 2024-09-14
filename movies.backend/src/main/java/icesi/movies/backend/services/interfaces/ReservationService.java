package icesi.movies.backend.services.interfaces;

import org.springframework.stereotype.Service;

import icesi.movies.backend.model.Reservation;

import java.util.List;

@Service
public interface ReservationService {
    
    Reservation createReservation(Long customerId, Long showtimeId, int seatCount);

    void cancelReservation(Long reservationId);

    void cancelUserReservation(Long id, Long custumerId);

    List<Reservation> getUserReservations(Long customerId);

    List<Reservation> getAllReservations();

}
