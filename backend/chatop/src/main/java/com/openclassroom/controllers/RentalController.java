package com.openclassroom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
	
	@GetMapping("api/rentals")
	public String getAllRentals() {
		return "Welcome, User";
	}
	
	@GetMapping("api/rentals/:id")
	public String getRentalById() {
		return "Welcome, User";
	}
	
	@PostMapping("api/rentals")
	public String postRental() {
		return "Welcome, User";
	}
	
	@PutMapping("api/rentals/:id")
	public String setRentalForId() {
		return "Welcome, User";
	}

}




