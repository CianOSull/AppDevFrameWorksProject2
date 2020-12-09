package ie.cian.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.cian.entities.County;
import ie.cian.forms.NewCountyForm;
import ie.cian.service.CountyService;

/*
 * This class handles forms, if a form has multiple fields,
 * then you will need to create multiple objects for it.
 * How these objects are created is in the html and this is below.
 */
public class NewCountyControllers {
	
	@Autowired 
	CountyService countyService;
	
	// The this is how you call it i htink in html
	@GetMapping("/newcounty")
	public String addACounty(Model model) 
	{
		// NewCountyForm is a new class
		model.addAttribute("newCountyForm", new NewCountyForm());
		return "newcounty";
	}
	
	// If a post message is sent ot his class, this will run
	@PostMapping("/newcounty")
	// This is taking in the NewCountyForm object that is
	// created in the html. 
	public String addACountyPost(NewCountyForm newCountyForm) 
	{
		// create and save a new county
		County county = countyService.save(newCountyForm.getNewCountyName());
		// This is redirects the new county id to county class i think
		return "redirect:county/" + county.getCountyId();
	}
	
	// Basically this method is trying to solve the issue of where the county already exists in the database i think
	// There is a flash attribute here which is redirect, so it only lasts for one request
	@PostMapping("/newcountry")
//	 This is supposed to solve inputting nothing but doesnt work because of not importing right class
//	public String addACountyPost(@Valid NewCountyForm newCountyForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes)
	public String addACountyPost(NewCountyForm newCountyForm, RedirectAttributes redirectAttributes)
	{
//		This is supposed to solve inputting nothing but doesnt work because of not importing right class
//		if(bindingResult.hasErrors())
//		{
//			go back to the claling page, taking the errors with you
//			return "newcounty";
//		}
		
		County county = countyService.save(newCountyForm.getNewCountyName());
		// If it already exists, create a flash attribute to last only for one redirect
		// Creates a new instance of the newCountyForm but only uses the duplicate county name once
		if (county == null)
		{
			redirectAttributes.addFlashAttribute("duplicateCountryName", newCountyForm.getNewCountyName());
			// go to newcounty controller, picking up a "clean" form on the way
			return "redirect:county/" + county.getCountyId();
		}
		return "redirect:county/" + county.getCountyId();
	}
}
