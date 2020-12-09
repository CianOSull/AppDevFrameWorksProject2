package ie.cian.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Indicates that it is a controller
// Serves a specialization of component
// Flags that everything in here is a controller
@Controller
public class IndexControllers {
	
	// What this is doing is that if you type just localhost8080
	// or just localhost8080/, this method will be called.
	// In the background Thymleaf, will know to put  .hmtl
	// at the end of the string returned.
	// This handles get requests
	/*
	 * Request Param:
	 * localhost8080?myname=Cian
	 * This handles getting data into the controller. 
	 * It handles request parameters.
	 * The model is the way to get data from the controller to the view
	 * M in MVC stands for model
	 * Example url: http://localhost:8080/?myname=Cian&myemail=cian.osullivan4@mycit.ie
	 * 
	 * Required means you dont need to input a valaue and will isntead use default
	 */
	@GetMapping("/")
	public String loadIndexGet(
			@RequestParam(name = "myname", required=false, defaultValue = "") String name, 
			@RequestParam(name = "myemail", required=false, defaultValue = "") String email, 
			Model model)
	{
		// This is now going to send the persons request with the value
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "index";
	}
	
	// This handles if a value is in the path e.g. 
	// http://localhost:8080/details/Cian/cian.osullivan4@mycit.ie
	@GetMapping("/details/{myname}/{myemail}")
	public String loadIndexGetPath(@PathVariable("myname") String name,
			@PathVariable("myemail") String email,
			Model model)
	{
		// This is now going to send the persons request with the value
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "index";
	}
	
	
	// This will handle post requests
	@PostMapping("/")
	public String loadIndexPost()
	{
		return "index";
	}
}
