package ie.cian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cian.entities.Director;
import ie.cian.entities.Film;

public interface FilmDao extends JpaRepository<Film, Integer>{
	// Find all towns by county_countyid, the '_' is basically a dot operator here
	// JPA automatically makes these methods
	// Get all the towns from the town side of the relationship
	List<Film> findAllByFilmDirector_DirectorId(int directorId);
	
	boolean existsByFilmNameAndFilmDirector(String filmName, Director director);
	
}
