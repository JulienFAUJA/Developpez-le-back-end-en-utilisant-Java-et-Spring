package com.openclassroom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.services.RentalService;
import com.openclassroom.services.UserService;

@RestController
@RequestMapping("/rentals")
public class RentalController {
	
	@Autowired
    private RentalService rentalService;
	

   
	
    
	
  
	
	@GetMapping
	public List<RentalDTO> getAllRentals() {
		List<RentalDTO> rentals = new ArrayList<>();
		rentalService.getRentals().forEach(r -> rentals.add(r));
		System.out.println("RentalController:"+rentals);
		return rentals;
	}
	

	@GetMapping("{id}")
	public RentalDTO getRentalById(@PathVariable("id") Integer id) {
		System.out.println("RentalController:"+rentalService.getRentalById(id).toString());
		return rentalService.getRentalById(id);
	}
	
	@PostMapping()
	public String postRental() {
		return "Welcome, User";
	}
	
	@PutMapping("{id}")
	public String setRentalForId(@PathVariable("id") Integer id) {
		return "Welcome, User";
	}

}




