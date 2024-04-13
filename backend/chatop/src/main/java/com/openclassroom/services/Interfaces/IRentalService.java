package com.openclassroom.services.Interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;

public interface IRentalService {

	ResponseEntity<?> getRentals();
	
	ResponseEntity<?> getRentalById(Integer id);
	
	ResponseEntity<?> postRental(MultipartFile picture, RentalDTO rentalDTO);
	
	ResponseEntity<?> updateRental(Integer id, RentalFormDTO rentalFormDTO);
}
