package dev.gimboscloud.bradsbikes.services;

import dev.gimboscloud.bradsbikes.models.Manufacturer;

public interface ManufacturerService {

	Iterable<Manufacturer> getAll();
	
	Manufacturer getManufacturerById(int id);
	
	Manufacturer save(Manufacturer manufacturer);
	
	void remove(Manufacturer manufacturer);
}
