package icesi.movies.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icesi.movies.backend.model.Customer;
import icesi.movies.backend.model.Reservation;
 
import java.util.List;  

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCustomer(Customer customer);

}
