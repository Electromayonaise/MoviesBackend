package icesi.movies.backend.services.interfaces;

import icesi.movies.backend.model.Movie;
import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    void deleteMovie(Long id);
}
