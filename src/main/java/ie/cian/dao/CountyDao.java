package ie.cian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ie.cian.entities.County;

public interface CountyDao extends JpaRepository<County, Integer>{
	County findCountyByCountyName(String countyName);
	// This one does the same as above, just a different way
	County findByCountyName(String countyName);
	County findByCountyId(int countyId);			
	int findCountyIdByCountyName(String countyName);
	boolean existsByCountyName(String countyName);
	boolean existsByCountyId(int countyId);
	
	List<County> findAllByOrderByCountyNameAsc();
	List<County> findAllByOrderByCountyNameDesc();
	// Can used to find all counties with c in their name
	List<County> findByCountyNameContainsAllIgnoreCase(String pattern);
	
	// All queries below are made manuelly and not jpa made
	
	// Serves as an example of JPQL because could be written using names: findCountyNameByCountyId()
	// Find name of a particular county by its id
	@Query("SELECT c.countyName FROM County c where c.countyId = :id") 
	String findNameOfCountyById(@Param("id") int id);
	
	// This is an example of native sql. you just need nativeQuery = True
	@Query(value = "SELECT * FROM county WHERE county.countyName LIKE :letter%", nativeQuery = true)
	List<County> findAllStartingWith(@Param("letter") char letter);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE County c SET c.countyName = :newName WHERE c.countyId = :countyId")
	void changeCountyName(@Param("newName") String newName, @Param("countyId") int countyId);
	
	// JOIN FETCH is a JPQL operator
	// Find all the county and its towns given a countyId 
	// Join fetch is jpql query, it gets all the countyTowns that belong to the county
	@Query("SELECT c FROM County c LEFT JOIN FETCH c.countyTowns t WHERE c.countyId = :countyId")
	County findCountyAndTownsByCountyId(int countyId);
	
	// Find all distinct counties and their towns. 
	// This is getting alll of the counties and all of the towns that belong to the counties
	@Query("SELECT DISTINCT c FROM County c JOIN FETCH c.countyTowns ")
	List<County> findAllCountiesAndTowns();
}
