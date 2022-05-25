package dev.gimboscloud.bradsbikes.processors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.models.Product;
import dev.gimboscloud.bradsbikes.services.AccessoryService;
import dev.gimboscloud.bradsbikes.services.ProductService;

public class AccessoryProcessorTest {
	
	private AccessoryService accessoryService = mock(AccessoryService.class);
	private AccessoryProcessor accessorysProcessor;

	@Before
	public void setUp() throws Exception {
		accessorysProcessor = new AccessoryProcessor(accessoryService);
	}

	@Test
	public void call_to_getAccessories_returns_iterable_of_accessorys() {
		List<MotorbikeAccessory> accessories = new ArrayList<>();
		accessories.add(new MotorbikeAccessory());
		
		when(accessoryService.getAll()).thenReturn(accessories);
		
		assertEquals(accessories, accessorysProcessor.getAccessories());
		verify(accessoryService, times(1)).getAll();
	}
	
	@Test
	public void call_to_getProductById_returns_accessory() {
		MotorbikeAccessory accessory = new MotorbikeAccessory();
		
		when(accessoryService.getAccessoryById(anyInt())).thenReturn(accessory);
		
		assertEquals(accessory, accessorysProcessor.getAccessoryById(1));
		verify(accessoryService, times(1)).getAccessoryById(1);
	}
	
	@Test
	public void call_to_updateProduct_calls_accessoryService() {
		MotorbikeAccessory accessory = new MotorbikeAccessory();
		
		accessorysProcessor.updateAccessory(accessory);
		
		verify(accessoryService, times(1)).save(accessory);
	}

}
