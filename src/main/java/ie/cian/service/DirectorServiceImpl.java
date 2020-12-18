package ie.cian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ie.cian.dao.DirectorDao;
import ie.cian.entities.Director;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	DirectorDao directorDao;
	
	@Override
	public List<Director> getAllDirectors() {
		return directorDao.findAllByOrderBySurnameAsc();
	}
	
	@Override
	public Director save(String firstname, String surname) {
		if (directorDao.existsBySurname(surname))
		{
			log.error("Attempt to add a director which already exists in the database");
			return null;
		}
		return directorDao.save(new Director(firstname, surname));
	}

	@Override
    public Director findDirector(int directorId) {
		Optional<Director> optional = directorDao.findById(directorId);
			
		if (optional.isPresent())
			return optional.get(); // note the use of .get()
		
		return null;
	}
	
	@Override
	public boolean changeDirectorName(String newName, int directorId) {
		Optional<Director> optional = directorDao.findById(directorId);
		if (directorDao.findBySurname(newName) == null && optional.isPresent())
		{
			directorDao.changeDirectorName(newName, directorId);
			return true;
		}
		return false;
	}

	

	@Override
	public long numberOfDirectors() {
		return directorDao.count();
	}
	

	@Override
	public List<Director> getAllDirectorsAlphabetically() {
		return directorDao.findAllByOrderBySurnameAsc();
	}

	
	@Override
	public List<Director> getAllDirectorsWithLetters(String letters) {
		return directorDao.findBySurnameContainsAllIgnoreCase(letters);
	}


	@Override
	public String getSurnameWithId(int id) {
		return directorDao.findNameOfDirectorById(id);
	}

	@Override
	public Director getDirectorById(int id) {
		Optional<Director> optional = directorDao.findById(id);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public boolean exists(String surname) {
		return directorDao.existsBySurname(surname);
	}

	@Override
	public Director getDirectorAndFilmsByDirectorId(int directorId) {
		return directorDao.findDirectorAndFilmsByDirectorId(directorId);
	}

	@Override
	public List<Director> getAllDirectorsAndTheirFilms() {
		return directorDao.findAllDirectorsAndFilms();
	}

	@Override
	public boolean deleteDirector(int directorId) {
		directorDao.deleteById(directorId);
		return directorDao.existsByDirectorId(directorId);
	}

	
}
