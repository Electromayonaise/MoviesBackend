package icesi.movies.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ShowtimeDto {
    private Long movieId;
    private Long theaterId;
    private LocalDate showDate;
    private LocalTime startTime;


}
