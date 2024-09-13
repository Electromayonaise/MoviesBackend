package icesi.movies.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icesi.movies.backend.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
