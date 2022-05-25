package dev.gimboscloud.bradsbikes.services;

import dev.gimboscloud.bradsbikes.models.Motorbike;

public interface MotorbikeService {

	Iterable<Motorbike> getAll();
	
	Motorbike getMotorbikeById(Integer id);
	
	Motorbike save(Motorbike product);
	
	void removeMotorbike(Integer id);
}
