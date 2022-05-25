package dev.gimboscloud.bradsbikes.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.services.AccessoryService;
import dev.gimboscloud.bradsbikes.services.MotorbikeService;

@Component
public class AccessoryProcessor {

	private AccessoryService accessoryService;
	
	@Autowired
	AccessoryProcessor(AccessoryService accessoryService) {
		this.accessoryService = accessoryService;
	}
	
	public Iterable<MotorbikeAccessory> getAccessories() {
		return this.accessoryService.getAll();
	}
	
	public MotorbikeAccessory getAccessoryById(Integer id) {
		return this.accessoryService.getAccessoryById(id);
	}
	
	public void updateAccessory(MotorbikeAccessory accessory) {
		this.accessoryService.save(accessory);
	}
	
}
