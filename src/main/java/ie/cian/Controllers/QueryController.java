package ie.cian.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.cian.entities.County;
import ie.cian.entities.Town;
import ie.cian.service.CountyService;
import ie.cian.service.TownService;

@Controller
public class QueryController {
	@Autowired
	CountyService countyService;
	
	@Autowired
	TownService townService;
	
	// This is so that you can pass in a countyId and get that
	// counties info
	@GetMapping("county/{countyId}")
	public String getCountyByCountyId(@PathVariable("countyId") int countyId, Model model)
	{
		County county = countyService.findCounty(countyId);
		if(county == null) {
			model.addAttribute("countyId", countyId);
			return "notfounderror";
		}
		model.addAttribute("county", county);
		return "county";
	}
	
	@GetMapping("/counties")
	public String showCounties(Model model)
	{
		List<County> counties = countyService.getAllCounties();
		model.addAttribute("counties", counties);
		return "counties";
	}
	
	@GetMapping("townsincounty/{countyId}")
	public String showTownsInCounty(@PathVariable(name="countyId") int countyId, Model model)
	{
		County county = countyService.getCountyAndTownsByCountyId(countyId);
		
		if(county == null) {
			model.addAttribute("countyId", countyId);
			return "notfounderror";
		}
		model.addAttribute("county", county);
		return "townsincounty";
	}
	
	@GetMapping("/town")
	public String showTownById(@PathVariable(name="townid", required = true) int townId, Model model)
	{
		Town town = townService.findTownByTownId(townId);
		if(town == null) {
			model.addAttribute("countyId", townId);
			return "notfounderror";
		}
		model.addAttribute("town", town);
		return "town";
	}
	
	// This is a delete request and is the main way deletions are done.
	// It takes a path.
	@DeleteMapping("/county/{countyId}")
	public String deleteMappingCounty(@PathVariable(name="countyId") int countyId, Model model)
	{
		if (countyService.deleteCounty(countyId)) {
			// Returning redirect is like a refresh,
			// so here it sends you back to counties
			// if a county is deleted.
			return "redirect:/counties";
		}
		model.addAttribute("countyId", countyId);
		return "notfounderror";
	}
	
	// This is not hte usual way for deleting and is jsut for demonstraiton
	@GetMapping("/county/delete/{countyId}")
	public String deleteCounty(@PathVariable(name="countyId") int countyId, Model model) 
	{
		if (countyService.deleteCounty(countyId)) {
			// Returning redirect is like a refresh,
			// so here it sends you back to counties
			// if a county is deleted.
			return "redirect:/counties";
		}
		model.addAttribute("countyId", countyId);
		return "notfounderror";
	}
	
}
