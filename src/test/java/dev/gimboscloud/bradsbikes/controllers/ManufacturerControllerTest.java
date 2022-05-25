package dev.gimboscloud.bradsbikes.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import dev.gimboscloud.bradsbikes.models.Manufacturer;
import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.models.Product;
import dev.gimboscloud.bradsbikes.services.ManufacturerService;

public class ManufacturerControllerTest {

	private ManufacturerService manufacturerService;
	private ManufacturerController manufacturerController;

	@Before
	public void setUp() throws Exception {
		manufacturerService = mock(ManufacturerService.class);
		manufacturerController = new ManufacturerController(manufacturerService);
	}

	@Test
	public void calling_serveManufacturerPage_responds_with_manufacturersPage() {
		final Model model = new ExtendedModelMap();
		
		assertEquals("manufacturers", manufacturerController.serveManufacturerPage(model));
	}
	
	@Test
	public void calling_serveManufacturerPage_responds_with_manufacturers_in_model() {
		final Model model = new ExtendedModelMap();

		List<Manufacturer> data = new ArrayList<>();
		data.add(new Manufacturer());
		Iterable<Manufacturer> dataIterable = data;
		
		when(manufacturerService.getAll()).thenReturn(dataIterable);
		
		assertEquals("manufacturers", manufacturerController.serveManufacturerPage(model));
		assertEquals(dataIterable, model.asMap().get("manufacturers"));
	}
	
	@Test
	public void calling_serveViewManaufacturerPage_returns_manufacturer_page() {
		final Model model = new ExtendedModelMap();
		
		Manufacturer manufacturer = new Manufacturer();
		Motorbike motorbike = new Motorbike("test", "test", 10, 10.00, 0);
		manufacturer.addProduct(motorbike);
		
		when(manufacturerService.getManufacturerById(anyInt())).thenReturn(manufacturer);
		
		assertEquals("viewmanufacturer", manufacturerController.serveViewManufacturerPage(model, 1));
		assertEquals(manufacturer, model.asMap().get("manufacturer"));
	}

}
