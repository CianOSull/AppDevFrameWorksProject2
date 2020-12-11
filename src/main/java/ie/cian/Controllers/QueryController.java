package ie.cian.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.cian.entities.Director;
import ie.cian.entities.Film;
import ie.cian.service.DirectorService;
import ie.cian.service.FilmService;

@Controller
public class QueryController {
	@Autowired
	DirectorService directorService;
	
	@Autowired
	FilmService filmService;
	
	// This is so that you can pass in a directorId and get that
	// directors info
	@GetMapping("director/{directorId}")
	public String getDirectorByDirectorId(@PathVariable("directorId") int directorId, Model model)
	{
		Director director = directorService.findDirector(directorId);
		if(director == null) {
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		model.addAttribute("director", director);
		return "director";
	}
	
	@GetMapping("/directors")
	public String showDirectors(Model model)
	{
		List<Director> directors = directorService.getAllDirectors();
		model.addAttribute("directors", directors);
		return "directors";
	}
	
	@GetMapping("filmsindirector/{directorId}")
	public String showFilmsInDirector(@PathVariable(name="directorId") int directorId, Model model)
	{
		Director director = directorService.getDirectorAndFilmsByDirectorId(directorId);
		
		if(director == null) {
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		model.addAttribute("director", director);
		return "filmsindirector";
	}
	
	@GetMapping("/film")
	public String showFilmById(@PathVariable(name="filmid", required = true) int filmId, Model model)
	{
		Film film = filmService.findFilmByFilmId(filmId);
		if(film == null) {
			model.addAttribute("directorId", filmId);
			return "notfounderror";
		}
		model.addAttribute("film", film);
		return "film";
	}
	
	// This is a delete request and is the main way deletions are done.
	// It takes a path.
	@DeleteMapping("/director/{directorId}")
	public String deleteMappingDirector(@PathVariable(name="directorId") int directorId, Model model)
	{
		if (directorService.deleteDirector(directorId)) {
			// Returning redirect is like a refresh,
			// so here it sends you back to directors
			// if a director is deleted.
			return "redirect:/directors";
		}
		model.addAttribute("directorId", directorId);
		return "notfounderror";
	}
	
	// This is not hte usual way for deleting and is jsut for demonstraiton
	@GetMapping("/director/delete/{directorId}")
	public String deleteDirector(@PathVariable(name="directorId") int directorId, Model model) 
	{
		if (directorService.deleteDirector(directorId)) {
			// Returning redirect is like a refresh,
			// so here it sends you back to directors
			// if a director is deleted.
			return "redirect:/directors";
		}
		model.addAttribute("directorId", directorId);
		return "notfounderror";
	}
	
}
