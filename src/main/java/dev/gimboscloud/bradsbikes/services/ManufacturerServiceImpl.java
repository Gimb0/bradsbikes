package dev.gimboscloud.bradsbikes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gimboscloud.bradsbikes.models.Manufacturer;
import dev.gimboscloud.bradsbikes.repositories.ManufacturerRepository;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
		this.manufacturerRepository = manufacturerRepository;
	}
	
	@Override
	public Iterable<Manufacturer> getAll() {
		return manufacturerRepository.findAll();
	}

	@Override
	public Manufacturer getManufacturerById(int id) {
		return manufacturerRepository.findById(id).orElse(null);
	}

	@Override
	public Manufacturer save(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}

	@Override
	public void remove(Manufacturer manufacturer) {
		manufacturerRepository.delete(manufacturer);
	}

}
