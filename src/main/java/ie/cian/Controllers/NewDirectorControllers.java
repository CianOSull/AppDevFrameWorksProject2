package ie.cian.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.cian.entities.Director;
import ie.cian.forms.NewDirectorForm;
import ie.cian.service.DirectorService;

/*
 * This class handles forms, if a form has multiple fields,
 * then you will need to create multiple objects for it.
 * How these objects are created is in the html and this is below.
 */
@Controller
public class NewDirectorControllers {
	
	@Autowired 
	DirectorService directorService;
	
	// The this is how you call it i htink in html
	@GetMapping("/newdirector")
	public String addADirector(Model model) 
	{
		// NewDirectorForm is a new class
		model.addAttribute("newDirectorForm", new NewDirectorForm());
		return "newdirector";
	}
	
	// If a post message is sent ot his class, this will run
	@PostMapping("/newdirector")
	// This is taking in the NewDirectorForm object that is
	// created in the html. 
	public String addADirectorPost(NewDirectorForm newDirectorForm) 
	{
		// create and save a new director
		Director director = directorService.save(newDirectorForm.getNewFirstname(), newDirectorForm.getNewSurname());
		// This is redirects the new director id to director class i think
		return "redirect:director/" + director.getDirectorId();
	}
	
	// Basically this method is trying to solve the issue of where the director already exists in the database i think
	// There is a flash attribute here which is redirect, so it only lasts for one request
//	@PostMapping("/newcountry")
////	 This is supposed to solve inputting nothing but doesnt work because of not importing right class
////	public String addADirectorPost(@Valid NewDirectorForm newDirectorForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes)
//	public String addADirectorPost(NewDirectorForm newDirectorForm, RedirectAttributes redirectAttributes)
//	{
////		This is supposed to solve inputting nothing but doesnt work because of not importing right class
////		if(bindingResult.hasErrors())
////		{
////			go back to the claling page, taking the errors with you
////			return "newdirector";
////		}
//		
//		Director director = directorService.save(newDirectorForm.getNewFirstname(), newDirectorForm.getNewSurname());
//		// If it already exists, create a flash attribute to last only for one redirect
//		// Creates a new instance of the newDirectorForm but only uses the duplicate director name once
//		if (director == null)
//		{
//			redirectAttributes.addFlashAttribute("duplicateCountryName", newDirectorForm.getNewSurname());
//			// go to newdirector controller, picking up a "clean" form on the way
//			return "redirect:director/" + director.getDirectorId();
//		}
//		return "redirect:director/" + director.getDirectorId();
//	}
}
