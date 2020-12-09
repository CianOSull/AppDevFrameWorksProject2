package ie.cian.service;

import java.util.List;

import ie.cian.entities.County;
import ie.cian.entities.Town;

public interface TownService {
	List<Town> getAllTowns();
	List<Town> getAllTownsInACounty(int countyId);
	Town save(String townName, County townCounty);
	Town findTownByTownId(int townId);
}
