package project.hh.filmreviewer.Services;

import project.hh.filmreviewer.Model.Films;
import project.hh.filmreviewer.Repository.FilmRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Films> getAllFilms() {
        return filmRepository.findAll();
    }

    public Films getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public Films addFilm(Films film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

}
