package dev.gimboscloud.bradsbikes.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.gimboscloud.bradsbikes.models.Motorbike;

public interface MotorbikeRepository extends CrudRepository<Motorbike, Integer> {

}
