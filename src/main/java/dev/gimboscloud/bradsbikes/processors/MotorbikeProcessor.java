package dev.gimboscloud.bradsbikes.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.services.MotorbikeService;

@Component
public class MotorbikeProcessor {

	private MotorbikeService motorbikeService;
	
	@Autowired
	MotorbikeProcessor(MotorbikeService motorbikeService) {
		this.motorbikeService = motorbikeService;
	}
	
	public Iterable<Motorbike> getMotorbikes() {
		return this.motorbikeService.getAll();
	}
	
	public Motorbike getMotorbikeById(Integer id) {
		return this.motorbikeService.getMotorbikeById(id);
	}
	
	public void updateMotorbike(Motorbike motorbike) {
		this.motorbikeService.save(motorbike);
	}
	
}
