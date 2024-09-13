package icesi.movies.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.services.interfaces.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Endpoint to create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(
            @RequestParam Long customerId,
            @RequestParam Long showtimeId,
            @RequestParam int seatCount) {
        
        Reservation reservation = reservationService.createReservation(customerId, showtimeId, seatCount);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // Endpoint to cancel a reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
