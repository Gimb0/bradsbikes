package dev.gimboscloud.bradsbikes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.repositories.MotorbikeRepository;

@Service
public class MotorbikeServiceImpl implements MotorbikeService {

	private MotorbikeRepository motorbikeRepository;
	
	@Autowired
	public MotorbikeServiceImpl(MotorbikeRepository motorbikeRepository) {
		this.motorbikeRepository = motorbikeRepository;
	}
	
	@Override
	public Iterable<Motorbike> getAll() {
		return motorbikeRepository.findAll();
	}

	@Override
	public Motorbike getMotorbikeById(Integer id) {
		return motorbikeRepository.findById(id).orElse(null);
	}

	@Override
	public Motorbike save(Motorbike motorbike) {
		return motorbikeRepository.save(motorbike);
	}

	@Override
	public void removeMotorbike(Integer id) {
		motorbikeRepository.deleteById(id);
	}

}
