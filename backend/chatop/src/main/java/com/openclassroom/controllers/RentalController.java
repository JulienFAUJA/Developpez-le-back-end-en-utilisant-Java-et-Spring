package com.openclassroom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.services.RentalService;
import com.openclassroom.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
    private RentalService rentalService;
	

	@GetMapping
	@ResponseBody
	public List<RentalDTO> getAllRentals() {
		System.out.println("Rentals...");
		List<RentalDTO> rentals = new ArrayList<>();
		rentalService.getRentals().forEach(r -> rentals.add(r));
		System.out.println("RentalController:"+rentals);
		return rentals;
	}
	
    
	
  
//	
//	@GetMapping(value ="/", consumes={"application/json"})
//	@ResponseBody
//	public List<RentalDTO> getAllRentals() {
//		System.out.println("Rentals...");
//		List<RentalDTO> rentals = new ArrayList<>();
//		rentalService.getRentals().forEach(r -> rentals.add(r));
//		System.out.println("RentalController:"+rentals);
//		return rentals;
//	}
	

	@GetMapping("{id}")
	public RentalDTO getRentalById(@PathVariable("id") Integer id) {
		System.out.println("RentalController:"+rentalService.getRentalById(id).toString());
		return rentalService.getRentalById(id);
	}
	
	@PostMapping()
	@ResponseBody
	public String postRental(@Valid @RequestBody RentalDTO rentalDTO) {
		return this.rentalService.postRental(rentalDTO);
	}
	
	@PutMapping("{id}")
	public String setRentalForId(@PathVariable("id") Integer id) {
		return "Welcome, User";
	}

}




