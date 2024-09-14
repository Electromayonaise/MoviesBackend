package icesi.movies.backend.controllers;

import icesi.movies.backend.model.Reservation;
import icesi.movies.backend.services.interfaces.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    void testCreateUserReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setSeatCount(4);

        when(reservationService.createReservation(1L, 1L, 4)).thenReturn(reservation);

        mockMvc.perform(post("/reservations/user")
                .param("showtimeId", "1")
                .param("seatCount", "4")
                .header("customerId", "1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.seatCount").value(4));

        verify(reservationService, times(1)).createReservation(1L, 1L, 4);
    }

    @Test
    void testCreateAdminReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(2L);
        reservation.setSeatCount(5);

        when(reservationService.createReservation(2L, 2L, 5)).thenReturn(reservation);

        mockMvc.perform(post("/reservations/admin")
                .param("customerId", "2")
                .param("showtimeId", "2")
                .param("seatCount", "5"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.seatCount").value(5));

        verify(reservationService, times(1)).createReservation(2L, 2L, 5);
    }

    @Test
    void testCancelUserReservation() throws Exception {
        doNothing().when(reservationService).cancelUserReservation(1L, 1L);

        mockMvc.perform(delete("/reservations/user/{id}", 1)
                .header("customerId", "1"))
                .andExpect(status().isNoContent());

        verify(reservationService, times(1)).cancelUserReservation(1L, 1L);
    }

    @Test
    void testCancelAdminReservation() throws Exception {
        doNothing().when(reservationService).cancelReservation(2L);

        mockMvc.perform(delete("/reservations/admin/{id}", 2))
                .andExpect(status().isNoContent());

        verify(reservationService, times(1)).cancelReservation(2L);
    }

    @Test
    void testGetUserReservations() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setSeatCount(3);

        when(reservationService.getUserReservations(1L)).thenReturn(Arrays.asList(reservation));

        mockMvc.perform(get("/reservations/user")
                .header("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].seatCount").value(3));

        verify(reservationService, times(1)).getUserReservations(1L);
    }

    @Test
    void testGetAllReservations() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(2L);
        reservation.setSeatCount(2);

        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(reservation));

        mockMvc.perform(get("/reservations/admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].seatCount").value(2));

        verify(reservationService, times(1)).getAllReservations();
    }
}
