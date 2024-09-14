package icesi.movies.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import icesi.movies.backend.model.Movie;
import icesi.movies.backend.model.Showtime;
import icesi.movies.backend.model.Theater;
import icesi.movies.backend.services.interfaces.ShowtimeService;
import icesi.movies.backend.repositories.MovieRepository;
import icesi.movies.backend.repositories.TheaterRepository;
import icesi.movies.backend.dto.ShowtimeDto;
import icesi.movies.backend.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody ShowtimeDto showtimeDto) {
        // Fetch Movie and Theater from their respective services or repositories
        Movie movie = movieRepository.findById(showtimeDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Theater theater = theaterRepository.findById(showtimeDto.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));

        // Create Showtime entity
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setTheater(theater);
        showtime.setShowDate(showtimeDto.getShowDate());
        showtime.setStartTime(showtimeDto.getStartTime());

        Showtime createdShowtime = showtimeService.createShowtime(showtime);
        return new ResponseEntity<>(createdShowtime, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id) {
        Showtime showtime = showtimeService.getShowtimeById(id);
        return new ResponseEntity<>(showtime, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/movie/{movieId}")
    public List<Showtime> getShowtimesByMovie(@PathVariable Long movieId) {
        return showtimeService.getShowtimesByMovie(movieId);
    }

}
