package icesi.movies.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.services.interfaces.ReservationService;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Endpoint for users to create their own reservation
    @PostMapping("/user")
    public ResponseEntity<Reservation> createUserReservation(
            @RequestParam Long showtimeId,
            @RequestParam int seatCount,
            @RequestHeader Long customerId) {
        // Ensure the logged-in user is creating their own reservation
        Reservation reservation = reservationService.createReservation(customerId, showtimeId, seatCount);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // Endpoint for admins to create a reservation for any user
    @PostMapping("/admin")
    public ResponseEntity<Reservation> createAdminReservation(
            @RequestParam Long customerId,
            @RequestParam Long showtimeId,
            @RequestParam int seatCount) {
        Reservation reservation = reservationService.createReservation(customerId, showtimeId, seatCount);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // Endpoint for users to cancel their own reservations
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> cancelUserReservation(@PathVariable Long id, @RequestHeader Long customerId) {
        reservationService.cancelUserReservation(id, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint for admins to cancel reservations of any user
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> cancelAdminReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Reservation>> getUserReservations(@RequestHeader Long customerId) {
        List<Reservation> reservations = reservationService.getUserReservations(customerId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }



}
