package ie.cian.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControllers {
	// For now just going to have the index do nothing
	// As it just needs to be a welcome page for now
	// But leaving this function her just in case for later
	@GetMapping("/")
	public String loadIndexGet()
	{
		return "index";
	}
}
