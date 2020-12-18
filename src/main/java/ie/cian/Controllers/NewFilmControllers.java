package ie.cian.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.cian.entities.Director;
import ie.cian.entities.Film;
import ie.cian.forms.NewDirectorForm;
import ie.cian.forms.NewFilmForm;
import ie.cian.service.DirectorService;
import ie.cian.service.FilmService;

/*
 * This class handles forms, if a form has multiple fields,
 * then you will need to create multiple objects for it.
 * How these objects are created is in the html and this is below.
 */
@Controller
public class NewFilmControllers {
	
	@Autowired 
	DirectorService directorService;
	
	@Autowired 
	FilmService townService;
	
	// The this is how you call it i htink in html
	@GetMapping("/newfilm")
	public String addNewFilm(Model model) 
	{
		model.addAttribute("newFilmForm", new NewFilmForm());
		model.addAttribute("directors", directorService.getAllDirectorsAlphabetically());		
		return "newfilm";
	}
	
	@PostMapping("/newfilm")
	public String addNewFilm(Model model, NewFilmForm newFilmForm, RedirectAttributes redirectAttributes)
	{
		Director director = directorService.findDirector(newFilmForm.getDirectorId());
		
		Film newFilm = townService.save(newFilmForm.getNewFilmName(), director);
		
		if (newFilm == null)
		{
			redirectAttributes.addFlashAttribute("duplicateFilm", newFilmForm.getNewFilmName());
			redirectAttributes.addFlashAttribute("duplicateDirector", director.getDirectorFullName());
		
			return "redirect:newfilmm";
		}
		
		return "redirect:film?filmid="+newFilm.getFilmId();
	}
	
	// Basically this method is trying to solve the issue of where the director already exists in the database i think
	// There is a flash attribute here which is redirect, so it only lasts for one request
//	@PostMapping("/newtown")
////	 This is supposed to solve inputting nothing but doesnt work because of not importing right class
//	public String addNewFilm(Model model, @Valid NewFilmForm newFilmForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes)
//	{
////		This is supposed to solve inputting nothing but doesnt work because of not importing right class
//		if(bindingResult.hasErrors())
//		{
////			go back to the calling/index page, taking the errors with you
//			model.addAttribute("counties", directorService.getAllCountiesAlphabetically());
//			return "newtown";
//		}
//		
//		Director director = directorService.save(newFilmForm.getNewDirectorId());
//		Film newFilm = townService.save(newFilmForm.getNewFilmNamem(), director);
//		
//		if(newFilm == null)
//		{
//			redirectAttributes.addFlashAttribute("duplicateFilm", newFilmForm.getNewFilmName());
//			redirectAttributes.addFlashAttribute("duplicateDirector", director.getDirectorName());
//			return "redirect:newtown";
//		}
//		
//		return "redirect:town?townid=" + newFilm.getFilmId();
//	}
}
