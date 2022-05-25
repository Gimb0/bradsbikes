package dev.gimboscloud.bradsbikes.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import dev.gimboscloud.bradsbikes.dto.BuyRequest;
import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.models.ShoppingCart;
import dev.gimboscloud.bradsbikes.processors.AccessoryProcessor;
import dev.gimboscloud.bradsbikes.processors.MotorbikeProcessor;

public class ProductsControllerTests {

	private ProductsController productsController;
	private MotorbikeProcessor motorbikeProcessor;
	private AccessoryProcessor accessoryProcessor;
	
	@Before
	public void setUpBeforeTest() throws Exception {
		motorbikeProcessor = mock(MotorbikeProcessor.class);
		accessoryProcessor = mock(AccessoryProcessor.class);
		productsController = new ProductsController(motorbikeProcessor, accessoryProcessor);
	}

	@Test
	public void call_to_serveMotorbikePage_returns_motorbikes() {
		String response = productsController.serveMotorbikePage(new ExtendedModelMap());
		
		assertEquals("/products", response);
	}
	
	@Test
	public void call_to_serveViewAccessoryPage_returns_accessory() {
		final Model model = new ExtendedModelMap();
		
		MotorbikeAccessory accessory = new MotorbikeAccessory();
		
		when(accessoryProcessor.getAccessoryById(anyInt())).thenReturn(accessory);
		
		String response = productsController.serveViewAccessoryPage(10, model);
		
		assertEquals("/viewproduct", response);
	}
	
	@Test
	public void call_to_serveViewAccessoryPage_returns_errorPage_on_wrong_accessoryID() {
		final Model model = new ExtendedModelMap();
		
		when(accessoryProcessor.getAccessoryById(anyInt())).thenReturn(null);
		
		String response = productsController.serveViewAccessoryPage(1000, model);
		
		assertEquals("/viewproducterror", response);
	}
	
	@Test
	public void model_from_serveMotorbikePage_contains_products() {
		final Model model = new ExtendedModelMap();
		
		List<Motorbike> data = new ArrayList<>();
		data.add(new Motorbike());
		Iterable<Motorbike> dataIterable = data;
		
		when(motorbikeProcessor.getMotorbikes()).thenReturn(dataIterable);
		
		assertEquals("/products", productsController.serveMotorbikePage(model));
		assertEquals("SUCCESS", model.asMap().get("status"));
		assertEquals(data, model.asMap().get("products"));
	}
	
	@Test
	public void model_from_serveMotorbikePage_contains_null() {
		final Model model = new ExtendedModelMap();
		
		List<Motorbike> data = new ArrayList<>();
		
		when(motorbikeProcessor.getMotorbikes()).thenReturn(data);
		
		assertEquals("/products", productsController.serveMotorbikePage(model));
		assertEquals("EMPTY", model.asMap().get("status"));
		assertEquals(data, model.asMap().get("products"));
	}
	
	@Test
	public void model_from_serveViewBikePage_contains_single_product() {
		final Model model = new ExtendedModelMap();
		
		Motorbike product = new Motorbike("Yamaha R1", "2020 Yamaha YZF-R1, 1000cc Superbike", 10, 20000.00, 195.0);
		
		when(motorbikeProcessor.getMotorbikeById(anyInt())).thenReturn(product);
		
		assertEquals("/viewproduct", productsController.serveViewBikePage(1, model));
		assertEquals(product, model.asMap().get("product"));
	}
	
	@Test
	public void call_to_serveViewBikePage_returns_error_on_invalid_productID() {
		final Model model = new ExtendedModelMap();
		
		when(motorbikeProcessor.getMotorbikeById(anyInt())).thenReturn(null);
		
		assertEquals("/viewproducterror", productsController.serveViewBikePage(1, model));
	}

	@Test
	public void call_to_handleBuyBike_creates_cart_with_bike() {
		final ShoppingCart cart = new ShoppingCart();
		
		Motorbike product = new Motorbike("Yamaha R1", "2020 Yamaha YZF-R1, 1000cc Superbike", 10, 20000.00, 195.0);
		
		when(motorbikeProcessor.getMotorbikeById(anyInt())).thenReturn(product);
		
		BuyRequest request = new BuyRequest();
		request.setProductID(1);
		request.setQuantity(1);
		
		assertEquals("redirect:/viewcart", productsController.handleBuyBike(cart, request));
	}
	
	@Test
	public void call_to_handleBuyBike_returns_error_page_on_invalid_productID() {
		BuyRequest request = new BuyRequest();
		request.setProductID(1);
		
		ShoppingCart cart = new ShoppingCart();
		
		when(motorbikeProcessor.getMotorbikeById(anyInt())).thenReturn(null);
		
		assertEquals("/viewproducterror", productsController.handleBuyBike(cart, request));
	}
	
	@Test
	public void call_to_serveAccessoryPage_returns_model_with_accessories() {
		final Model model = new ExtendedModelMap();
		
		List<MotorbikeAccessory> data = new ArrayList<>();
		data.add(new MotorbikeAccessory());
		Iterable<MotorbikeAccessory> dataIterable = data;
		
		when(accessoryProcessor.getAccessories()).thenReturn(dataIterable);
		
		assertEquals("/products", productsController.serveAccessoryPage(model));
		assertEquals("SUCCESS", model.asMap().get("status"));
		assertEquals(data, model.asMap().get("products"));
	}
	
	@Test
	public void model_from_serveAccessoryPage_contains_null() {
		final Model model = new ExtendedModelMap();
		
		List<MotorbikeAccessory> data = new ArrayList<>();
		Iterable<MotorbikeAccessory> dataIterable = data;
		
		when(accessoryProcessor.getAccessories()).thenReturn(dataIterable);
		
		assertEquals("/products", productsController.serveAccessoryPage(model));
		assertEquals("EMPTY", model.asMap().get("status"));
		assertEquals(data, model.asMap().get("products"));
	}
	
	@Test
	public void call_to_serveCartPage_returns_cart() {
		final Model model = new ExtendedModelMap();
		
		ShoppingCart cart = new ShoppingCart();
		
		assertEquals("/viewcart", productsController.serveCartPage(cart, model));
	}
	
	@Test
	public void call_to_handleBuyBike_with_quantity_ZERO_removes_product_from_cart() {
		ShoppingCart cart = mock(ShoppingCart.class);
		Motorbike product = new Motorbike();
		product.setId(1);
		product.setQuantity(3);
		cart.addProduct(product, 3);
		
		BuyRequest request = new BuyRequest();
		request.setProductID(product.getId());
		request.setQuantity(0);
		
		when(cart.getProductQuantity(product)).thenReturn(3);
		when(motorbikeProcessor.getMotorbikeById(anyInt())).thenReturn(product);
		
		assertEquals("redirect:/viewcart", productsController.handleBuyBike(cart, request));
		
		InOrder order = inOrder(motorbikeProcessor);
		
		order.verify(motorbikeProcessor).getMotorbikeById(anyInt());
		order.verify(motorbikeProcessor).updateMotorbike(any());
	}
	
	@Test
	public void call_to_handleBuyAccessory_adds_accessory_to_cart() {
		ShoppingCart cart = mock(ShoppingCart.class);
		MotorbikeAccessory product = new MotorbikeAccessory();
		product.setId(1);
		product.setQuantity(3);
		cart.addProduct(product, 3);
		
		BuyRequest request = new BuyRequest();
		request.setProductID(product.getId());
		request.setQuantity(2);
		
		when(cart.getProductQuantity(product)).thenReturn(3);
		when(accessoryProcessor.getAccessoryById(anyInt())).thenReturn(product);
		
		assertEquals("redirect:/viewcart", productsController.handleBuyAccessory(cart, request));
		
		InOrder order = inOrder(accessoryProcessor);
		
		order.verify(accessoryProcessor).getAccessoryById(anyInt());
		order.verify(accessoryProcessor).updateAccessory(any());
	}
	
	@Test
	public void call_to_handleBuyAccessory_with_ZERO_quantity_removes_product_from_cart() {
		ShoppingCart cart = mock(ShoppingCart.class);
		MotorbikeAccessory product = new MotorbikeAccessory();
		product.setId(1);
		product.setQuantity(3);
		cart.addProduct(product, 3);
		
		BuyRequest request = new BuyRequest();
		request.setProductID(product.getId());
		request.setQuantity(0);
		
		when(cart.getProductQuantity(product)).thenReturn(3);
		when(accessoryProcessor.getAccessoryById(anyInt())).thenReturn(product);
		
		assertEquals("redirect:/viewcart", productsController.handleBuyAccessory(cart, request));
		
		InOrder order = inOrder(accessoryProcessor);
		
		order.verify(accessoryProcessor).getAccessoryById(anyInt());
		order.verify(accessoryProcessor).updateAccessory(any());
	}
	
	@Test
	public void call_to_handleBuyAccessory_with_invalid_productID_redirects_with_error_page() {
		ShoppingCart cart = mock(ShoppingCart.class);
		MotorbikeAccessory product = new MotorbikeAccessory();
		product.setId(1);
		product.setQuantity(3);
		cart.addProduct(product, 3);
		
		BuyRequest request = new BuyRequest();
		request.setProductID(100);
		request.setQuantity(0);
		
		when(cart.getProductQuantity(product)).thenReturn(3);
		when(accessoryProcessor.getAccessoryById(anyInt())).thenReturn(null);
		
		assertEquals("/viewproducterror", productsController.handleBuyAccessory(cart, request));
	}
}
