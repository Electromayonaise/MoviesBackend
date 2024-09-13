package icesi.movies.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icesi.movies.backend.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
