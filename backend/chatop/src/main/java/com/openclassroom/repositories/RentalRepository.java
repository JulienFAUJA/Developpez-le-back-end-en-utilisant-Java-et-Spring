package com.openclassroom.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.models.RentalModel;

@Repository
public interface RentalRepository extends CrudRepository<RentalModel, Integer> {
	public Optional<RentalModel> findById(Integer id);

}





