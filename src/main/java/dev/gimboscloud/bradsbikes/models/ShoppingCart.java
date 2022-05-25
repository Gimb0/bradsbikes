package dev.gimboscloud.bradsbikes.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

	Map<Product, Integer> products;
	
	public ShoppingCart() {
		products = new HashMap<>();
	}
	
	public void addProduct(Product product, Integer quantity) {
		products.put(product, quantity);
	}
	
	public void addAccessory(MotorbikeAccessory accessory, Motorbike motorbike, Integer quantity) {
		// Create list with 1 motobike
		List<Motorbike> motorbikes = new ArrayList<>();
		motorbikes.add(motorbike);
		accessory.setMotorbikes(motorbikes);
		products.put(accessory, quantity);
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	public Integer getProductQuantity(Product product) {
		return products.get(product);
	}
}
