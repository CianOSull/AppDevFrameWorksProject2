package ie.cian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cian.dao.TownDao;
import ie.cian.entities.County;
import ie.cian.entities.Town;

@Service
public class TownServiceImplementation implements TownService{

	@Autowired
	TownDao townDao;
	
	@Override
	public Town save(String townName, County townCounty) {
		if (townDao.existsByTownNameAndTownCounty(townName, townCounty))
		{
			return null;
		}
		Town newTown = new Town(townName, townCounty);
		return townDao.save(newTown);	
	}

	@Override
	public List<Town> getAllTowns() {
		return townDao.findAll();
	}

	@Override
	public List<Town> getAllTownsInACounty(int countyId) {
		return townDao.findAllByTownCounty_CountyId(countyId);
	}
	
	public Town findTownByTownId(int townId)
	{
		Optional<Town> optional = townDao.findById(townId);
		
		if (optional.isPresent())
			return optional.get(); // note the use of .get()
		
		return null;
	}
	
}
