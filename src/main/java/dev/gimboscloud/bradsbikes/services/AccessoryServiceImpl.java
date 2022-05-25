package dev.gimboscloud.bradsbikes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.repositories.AccessoryRepository;

@Service
public class AccessoryServiceImpl implements AccessoryService {

	private AccessoryRepository accessoryeRepository;
	
	@Autowired
	public AccessoryServiceImpl(AccessoryRepository accessoryeRepository) {
		this.accessoryeRepository = accessoryeRepository;
	}
	
	@Override
	public Iterable<MotorbikeAccessory> getAll() {
		return accessoryeRepository.findAll();
	}

	@Override
	public MotorbikeAccessory getAccessoryById(Integer id) {
		return accessoryeRepository.findById(id).orElse(null);
	}

	@Override
	public MotorbikeAccessory save(MotorbikeAccessory accessorye) {
		return accessoryeRepository.save(accessorye);
	}

	@Override
	public void removeAccessory(Integer id) {
		accessoryeRepository.deleteById(id);
	}

}
