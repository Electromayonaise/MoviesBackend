package icesi.movies.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icesi.movies.backend.model.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
