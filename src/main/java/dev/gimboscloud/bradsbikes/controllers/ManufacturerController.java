package dev.gimboscloud.bradsbikes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.gimboscloud.bradsbikes.models.Manufacturer;
import dev.gimboscloud.bradsbikes.services.ManufacturerService;

@Controller
public class ManufacturerController {

	private ManufacturerService manufacturerService;
	
	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
	@GetMapping("/manufacturers")
	public String serveManufacturerPage(Model model) {
		model.addAttribute("manufacturers", manufacturerService.getAll());
		return "manufacturers";
	}

	@GetMapping("/manufacturers/view/{id}")
	public String serveViewManufacturerPage(Model model, @PathVariable Integer id) {
		Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
		model.addAttribute("manufacturer", manufacturer);
		return "viewmanufacturer";
	}
}
