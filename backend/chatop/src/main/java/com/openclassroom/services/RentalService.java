package com.openclassroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.models.RentalModel;
import com.openclassroom.repositories.RentalRepository;


@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	public Iterable<RentalModel> getRentals(){
		return rentalRepository.findAll();
	}

}
