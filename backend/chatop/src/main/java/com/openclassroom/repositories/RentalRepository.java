package com.openclassroom.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.models.RentalModel;

@Repository
public interface RentalRepository extends CrudRepository<RentalModel, Integer> {
//	public Optional<RentalDTO> findById(Integer id);
//	public List<RentalDTO> findAll();

}





