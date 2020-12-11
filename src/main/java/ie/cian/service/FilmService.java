package ie.cian.service;

import java.util.List;

import ie.cian.entities.Director;
import ie.cian.entities.Film;

public interface FilmService {
	List<Film> getAllFilms();
	List<Film> getAllFilmsByADirector(int directorId);
	Film save(String filmName, Director filmDirector);
	Film findFilmByFilmId(int filmId);
}
