package icesi.movies.backend.services.interfaces;

import icesi.movies.backend.model.Reservation;

public interface ReservationService {
    
    Reservation createReservation(Long customerId, Long showtimeId, int seatCount);

    void cancelReservation(Long reservationId);
}
