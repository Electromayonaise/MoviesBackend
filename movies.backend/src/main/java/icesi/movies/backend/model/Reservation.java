package icesi.movies.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatCount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    // Helper method to get the customer ID
    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }
}
