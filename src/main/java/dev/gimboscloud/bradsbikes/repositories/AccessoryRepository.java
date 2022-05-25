package dev.gimboscloud.bradsbikes.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;

public interface AccessoryRepository extends CrudRepository<MotorbikeAccessory, Integer> {

}
