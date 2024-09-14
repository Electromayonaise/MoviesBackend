package icesi.movies.backend.services.interfaces;

import icesi.movies.backend.model.Showtime;
import java.util.List;

public interface ShowtimeService {
    Showtime createShowtime(Showtime showtime);
    List<Showtime> getAllShowtimes();
    Showtime getShowtimeById(Long id);
    void deleteShowtime(Long id);
    public List<Showtime> getShowtimesByMovie(Long movieId);
}
