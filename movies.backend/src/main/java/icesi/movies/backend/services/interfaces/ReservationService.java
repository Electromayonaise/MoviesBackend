package icesi.movies.backend.services.interfaces;

import org.springframework.stereotype.Service;

import icesi.movies.backend.model.Reservation;

@Service
public interface ReservationService {
    
    Reservation createReservation(Long customerId, Long showtimeId, int seatCount);

    void cancelReservation(Long reservationId);

    void cancelUserReservation(Long id, Long custumerId);
}
