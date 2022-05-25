package dev.gimboscloud.bradsbikes.services;

import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;

public interface AccessoryService {

	Iterable<MotorbikeAccessory> getAll();
	
	MotorbikeAccessory getAccessoryById(Integer id);
	
	MotorbikeAccessory save(MotorbikeAccessory product);
	
	void removeAccessory(Integer id);
}
