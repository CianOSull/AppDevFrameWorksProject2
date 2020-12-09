package ie.cian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cian.entities.County;
import ie.cian.entities.Town;

public interface TownDao extends JpaRepository<Town, Integer>{
	// Find all towns by county_countyid, the '_' is basically a dot operator here
	// JPA automatically makes these methods
	// Get all the towns from the town side of the relationship
	List<Town> findAllByTownCounty_CountyId(int countyId);
	
	boolean existsByTownNameAndTownCounty(String townName, County county);
	
}
