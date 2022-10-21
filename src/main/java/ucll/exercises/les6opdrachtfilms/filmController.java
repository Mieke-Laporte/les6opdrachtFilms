package ucll.exercises.les6opdrachtfilms;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("films")
public class filmController {
    private static FilmService filmService = new FilmService();

    @PostMapping(value ="/" , consumes = {"application/xml","application/json"})
    public ResponseEntity createMovie(@RequestBody Film film) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String title = film.getTitle();
        int year = film.getYear();
        int rating = film.getRating();
        String description = film.getDescription();
        try{
            filmService.sCreateMovie(title, year, rating, description);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public List<Film> getAllMovies(){
        List<Film> films = new ArrayList<>();
        try{
            List<Film> dbFilms = filmService.sGetAllMovies();
            films.addAll(dbFilms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<String>> getMovieByTitle(@PathVariable String title){
        List<String> stringFilm =new ArrayList<>();
        try {
            Film film = filmService.sGetByTitle(title);
            stringFilm.add(film.getTitle());
            stringFilm.add(film.getYear().toString());
            stringFilm.add(film.getRating().toString());
            stringFilm.add(film.getDescription());
            return new ResponseEntity<>(stringFilm, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(stringFilm, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{title}")
    public ResponseEntity updateMovie(@PathVariable(name = "title", required = true) String title, @RequestBody Film film) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try{
            filmService.sUpdateMovie(film.getTitle(), film.getYear(), film.getRating(), film.getDescription());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
