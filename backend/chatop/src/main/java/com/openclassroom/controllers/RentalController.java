package com.openclassroom.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;
import com.openclassroom.dto.RentalResponseDTO;
import com.openclassroom.dto.RentalsResponseDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.services.RentalService;
import com.openclassroom.services.UserService;
import com.openclassroom.models.RentalModel;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
    private RentalService rentalService;
	

	@GetMapping
	@ResponseBody
	public ResponseEntity<RentalsResponseDTO> getAllRentals() {
		System.out.println("Rentals...");
		List<RentalFormDTO> rentals = rentalService.getRentals();
		RentalsResponseDTO response = new RentalsResponseDTO(rentals);
//		List<RentalFormDTO> rentals_forms = new ArrayList<>();
//		rentalService.getRentals().forEach(r -> rentals_forms.add(r));
		System.out.println("RentalController:"+response);
		return ResponseEntity.ok(response);
	}
	
    
	
  

	@GetMapping("{id}")
	public RentalFormDTO getRentalById(@PathVariable("id") Integer id) {
		System.out.println("RentalController:"+rentalService.getRentalById(id).toString());
		return rentalService.getRentalById(id);
	}
	
//	@PostMapping
//	public String postRental(
//			 RentalDTO rentalDTO) throws IOException {
//		System.out.println("rentalDTO:"+rentalDTO.toString());
//		return this.rentalService.postRental(rentalDTO.getPicture(), rentalDTO);
//	}
	
	@PostMapping
	public RentalResponseDTO postRental(RentalDTO rentalDTO) throws IOException {
		System.out.println("rentalDTO:"+rentalDTO.toString());
		//return this.rentalService.postRental(rentalDTO.getPicture(), rentalDTO);
		String rentalMessage = rentalService.postRental(rentalDTO.getPicture(), rentalDTO);
		RentalResponseDTO rentalResponseDTO= new RentalResponseDTO(rentalMessage);
		System.out.print("RentalController:"+rentalResponseDTO.getMessage());
		return rentalResponseDTO;
	}
	
	@PutMapping(value="{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public RentalResponseDTO setRentalForId(@PathVariable("id") Integer id, RentalDTO rentalDTO) {
		String rentalMessage = rentalService.postRental(rentalDTO.getPicture(), rentalDTO);
		RentalResponseDTO rentalResponseDTO= new RentalResponseDTO(rentalMessage);
		System.out.print("RentalController:"+rentalResponseDTO.getMessage());
		return rentalResponseDTO;
	}

}




