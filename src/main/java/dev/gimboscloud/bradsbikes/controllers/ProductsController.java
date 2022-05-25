package dev.gimboscloud.bradsbikes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import dev.gimboscloud.bradsbikes.dto.BuyRequest;
import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.models.Product;
import dev.gimboscloud.bradsbikes.models.ShoppingCart;
import dev.gimboscloud.bradsbikes.processors.AccessoryProcessor;
import dev.gimboscloud.bradsbikes.processors.MotorbikeProcessor;

@Controller
@SessionAttributes("cart")
public class ProductsController {

	private MotorbikeProcessor motorbikeProcessor;
	private AccessoryProcessor accessoryProcessor;

	@Autowired
	public ProductsController(MotorbikeProcessor motorbikeProcessor, AccessoryProcessor accessoryProcessor) {
		this.motorbikeProcessor = motorbikeProcessor;
		this.accessoryProcessor = accessoryProcessor;
	}

	@GetMapping("/bikes")
	public String serveMotorbikePage(Model model) {
		Iterable<Motorbike> products = motorbikeProcessor.getMotorbikes();

		if (products.iterator().hasNext())
			model.addAttribute("status", "SUCCESS");
		else
			model.addAttribute("status", "EMPTY");

		model.addAttribute("products", products);

		return "/products";
	}
	
	@GetMapping("/accessories")
	public String serveAccessoryPage(Model model) {
		Iterable<MotorbikeAccessory> products = accessoryProcessor.getAccessories();

		if (products.iterator().hasNext())
			model.addAttribute("status", "SUCCESS");
		else
			model.addAttribute("status", "EMPTY");

		model.addAttribute("products", products);

		return "/products";
	}

	@GetMapping("/bikes/view/{id}")
	public String serveViewBikePage(@PathVariable Integer id, Model model) {
		Product product = motorbikeProcessor.getMotorbikeById(id);

		if (product != null) {
			model.addAttribute("product", product);
			BuyRequest request = new BuyRequest();
			request.setProductID(id);
			model.addAttribute("buyRequest", request);
			return "/viewproduct";
		} else {
			return "/viewproducterror";
		}
	}
	
	@GetMapping("/accessories/view/{id}")
	public String serveViewAccessoryPage(@PathVariable Integer id, Model model) {
		MotorbikeAccessory accessory = accessoryProcessor.getAccessoryById(id);

		if (accessory != null) {
			model.addAttribute("product", accessory);
			BuyRequest request = new BuyRequest();
			request.setProductID(id);
			model.addAttribute("buyRequest", request);
			return "/viewproduct";
		} else {
			return "/viewproducterror";
		}
	}

	@PostMapping(value = "/bikes/buy")
	public String handleBuyBike(@ModelAttribute("cart") ShoppingCart cart, BuyRequest request) {
		Motorbike motorbike = motorbikeProcessor.getMotorbikeById(request.getProductID());
		
		if (motorbike != null) {
			if(request.getQuantity() == 0) {
				Integer quantity = cart.getProductQuantity(motorbike);
				if(quantity > 0) {
					motorbike.setQuantity(motorbike.getQuantity() + quantity);
					cart.removeProduct(motorbike);
					motorbikeProcessor.updateMotorbike(motorbike);
				}
			} else {
				if(motorbike.getQuantity() >= request.getQuantity()) {
					motorbike.setQuantity(motorbike.getQuantity() - request.getQuantity());
					motorbikeProcessor.updateMotorbike(motorbike);
					cart.addProduct(motorbike, request.getQuantity());
				}
			}
			return "redirect:/viewcart";
		} else {
			return "/viewproducterror";
		}
	}
	
	@PostMapping(value = "/accessories/buy")
	public String handleBuyAccessory(@ModelAttribute("cart") ShoppingCart cart, BuyRequest request) {
		MotorbikeAccessory accessory = accessoryProcessor.getAccessoryById(request.getProductID());
		
		// Check product exists
		if (accessory != null) {
			// Check for empty quantity (remove product)
			if(request.getQuantity() == 0) {
				Integer quantity = cart.getProductQuantity(accessory);
				if(quantity > 0) {
					accessory.setQuantity(accessory.getQuantity() + quantity);
					cart.removeProduct(accessory);
					accessoryProcessor.updateAccessory(accessory);
				}
			// Add product to session cart
			} else {
				if(accessory.getQuantity() >= request.getQuantity()) {
					accessory.setQuantity(accessory.getQuantity() - request.getQuantity());
					accessoryProcessor.updateAccessory(accessory);
					Motorbike motorbike = (Motorbike) motorbikeProcessor.getMotorbikeById(request.getMotorbikeID());
					cart.addAccessory(accessory, motorbike, request.getQuantity());
				}
			}
			return "redirect:/viewcart";
		} else {
			return "/viewproducterror";
		}
	}
	
	@GetMapping("/viewcart")
	public String serveCartPage(@ModelAttribute("cart") ShoppingCart cart, Model model) {
		model.addAttribute("productsMap", cart.getProducts());
		return "/viewcart";
	}

	@ModelAttribute("cart")
	private ShoppingCart cart() {
		return new ShoppingCart();
	}

}
