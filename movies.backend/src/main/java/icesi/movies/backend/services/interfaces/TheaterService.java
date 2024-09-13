package icesi.movies.backend.services.interfaces;

import icesi.movies.backend.model.Theater;
import java.util.List;

public interface TheaterService {
    Theater createTheater(Theater theater);
    List<Theater> getAllTheaters();
    Theater getTheaterById(Long id);
    void deleteTheater(Long id);
}
