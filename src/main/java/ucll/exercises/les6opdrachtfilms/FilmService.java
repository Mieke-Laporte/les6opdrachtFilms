package ucll.exercises.les6opdrachtfilms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FilmService {
    private static FilmRepository filmRepository = new FilmRepository();
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    private String apiString = "http://www.omdbapi.com/?apikey=f71e4482";
    public Object[] findAllFilmsComplete() {
        return restTemplate.getForObject(apiString, Object[].class);
    }

    public boolean sCreateMovie(String title, int year, int rating, String description) {
        boolean result = false;
        try {
            //API
            apiString += title;
            apiString +=year;
            List<Object> list =new ArrayList<>();
            result = filmRepository.rCreateMovie(title, year, rating, description );
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Film> sGetAllMovies(){
        List<Film> films = new ArrayList<>();
        try {
            films.addAll(filmRepository.rGetAllMovies());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  films;
    }

    public Film sGetByTitle(String title){
        Film film = new Film();
        try{
            film = filmRepository.rGetByTitle(title);
        } catch (Exception e){
            e.printStackTrace();
        }
        return film;
    }

    public boolean sUpdateMovie(String title, int year, int rating, String description){
        boolean result = false;
        try{
            result = filmRepository.rUpdateMovie( title, year, rating, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
