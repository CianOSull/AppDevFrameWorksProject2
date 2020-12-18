package ie.cian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.dao.FilmDao;
import ie.cian.entities.Director;
import ie.cian.entities.Film;

@Service
public class FilmServiceImplementation implements FilmService{

	@Autowired
	FilmDao filmDao;
	
	@Override
    public Film findFilm(int filmId) {
		Optional<Film> optional = filmDao.findById(filmId);
			
		if (optional.isPresent())
			return optional.get(); // note the use of .get()
		// If nothing then just return null
		return null;
	}
	
	@Override
	public Film save(String filmName, Director filmDirector) {
		if (filmDao.existsByFilmNameAndFilmDirector(filmName, filmDirector))
		{
			return null;
		}
		Film newFilm = new Film(filmName, filmDirector);
		return filmDao.save(newFilm);	
	}

	@Override
	public List<Film> getAllFilms() {
		return filmDao.findAll();
	}

	@Override
	public List<Film> getAllFilmsByADirector(int directorId) {
		return filmDao.findAllByFilmDirector_DirectorId(directorId);
	}
	
	public Film findFilmByFilmId(int filmId)
	{
		Optional<Film> optional = filmDao.findById(filmId);
		
		if (optional.isPresent())
			return optional.get(); // note the use of .get()
		
		return null;
	}
	
}
