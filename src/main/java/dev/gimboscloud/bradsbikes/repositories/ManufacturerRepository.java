package dev.gimboscloud.bradsbikes.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.gimboscloud.bradsbikes.models.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer> {

}
