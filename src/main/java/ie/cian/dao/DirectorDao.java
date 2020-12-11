package ie.cian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.cian.entities.Director;

public interface DirectorDao extends JpaRepository<Director, Integer>{
	Director findDirectorBySurname(String countyName);
	// This one does the same as above, just a different way
	Director findBySurname(String countyName);
	Director findByDirectorId(int countyId);			
	int findDirectorIdBySurname(String countyName);
	boolean existsBySurname(String countyName);
	boolean existsByDirectorId(int countyId);
	
	List<Director> findAllByOrderBySurnameAsc();
	List<Director> findAllByOrderBySurnameDesc();
	List<Director> findBySurnameContainsAllIgnoreCase(String pattern);
	
	@Query("SELECT d.surname FROM Director d where d.directorId = :id") 
	String findNameOfDirectorById(@Param("id") int id);
	
	// This is an example of native sql. you just need nativeQuery = True
	@Query(value = "SELECT * FROM director WHERE director.surname LIKE :letter%", nativeQuery = true)
	List<Director> findAllStartingWith(@Param("letter") char letter);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Director d SET d.surname = :newSurname WHERE d.directorId = :directorId")
	void changeDirectorName(@Param("newSurname") String newName, @Param("directorId") int directorId);
	
	// JOIN FETCH is a JPQL operator
	// Find all the county and its towns given a countyId 
	// Join fetch is jpql query, it gets all the countyFilms that belong to the county
	@Query("SELECT d FROM Director d LEFT JOIN FETCH d.directorFilms df WHERE d.directorId = :directorId")
	Director findDirectorAndFilmsByDirectorId(int directorId);
	
	// Find all distinct counties and their towns. 
	// This is getting alll of the counties and all of the towns that belong to the counties
	@Query("SELECT DISTINCT d FROM Director d JOIN FETCH d.directorFilms ")
	List<Director> findAllDirectorsAndFilms();
}
