package ie.cian.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.cian.entities.County;
import ie.cian.entities.Town;
import ie.cian.forms.NewCountyForm;
import ie.cian.forms.NewTownForm;
import ie.cian.service.CountyService;
import ie.cian.service.TownService;

/*
 * This class handles forms, if a form has multiple fields,
 * then you will need to create multiple objects for it.
 * How these objects are created is in the html and this is below.
 */
public class NewTownControllers {
	
	@Autowired 
	CountyService countyService;
	
	@Autowired 
	TownService townService;
	
	// The this is how you call it i htink in html
	@GetMapping("/newtown")
	public String addNewTown(Model model) 
	{
		model.addAttribute("newTownForm", new NewTownForm());
		model.addAttribute("counties", countyService.getAllCountiesAlphabetically());		
		return "newtown";
	}
	
	// Basically this method is trying to solve the issue of where the county already exists in the database i think
	// There is a flash attribute here which is redirect, so it only lasts for one request
	@PostMapping("/newtown")
//	 This is supposed to solve inputting nothing but doesnt work because of not importing right class
	public String addNewTown(Model model, @Valid NewTownForm newTownForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes)
	{
//		This is supposed to solve inputting nothing but doesnt work because of not importing right class
		if(bindingResult.hasErrors())
		{
//			go back to the calling/index page, taking the errors with you
			model.addAttribute("counties", countyService.getAllCountiesAlphabetically());
			return "newtown";
		}
		
		County county = countyService.save(newTownForm.getNewCountyId());
		Town newTown = townService.save(newTownForm.getNewTownNamem(), county);
		
		if(newTown == null)
		{
			redirectAttributes.addFlashAttribute("duplicateTown", newTownForm.getNewTownName());
			redirectAttributes.addFlashAttribute("duplicateCounty", county.getCountyName());
			return "redirect:newtown";
		}
		
		return "redirect:town?townid=" + newTown.getTownId();
	}
}
