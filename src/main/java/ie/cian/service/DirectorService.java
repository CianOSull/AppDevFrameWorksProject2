package ie.cian.service;

import java.util.List;

import ie.cian.entities.Director;

public interface DirectorService {
	Director save(String firstname, String surname);
	List<Director> getAllDirectors();
	List<Director> getAllDirectorsAndTheirFilms();
	List<Director> getAllDirectorsAlphabetically();
	boolean deleteDirector(int directorId);
	long numberOfDirectors();
	Director findDirector(int directorId);
	boolean changeDirectorName(String newName, int directorId);
	List<Director> getAllDirectorsWithLetters(String letters);
	String getSurnameWithId(int id);
	Director getDirectorById(int id);
	boolean exists(String surname);
	Director getDirectorAndFilmsByDirectorId(int directorId);
}
