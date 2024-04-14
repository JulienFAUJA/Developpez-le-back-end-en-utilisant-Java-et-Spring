package com.openclassroom.services.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;
import com.openclassroom.dto.RentalResponseDTO;
import com.openclassroom.dto.RentalsResponseDTO;

public interface IRentalService {

	RentalsResponseDTO getRentals();
	
	RentalFormDTO getRentalById(Integer id);
	
	RentalResponseDTO postRental(MultipartFile picture, RentalDTO rentalDTO);
	
	RentalResponseDTO updateRental(Integer id, RentalFormDTO rentalFormDTO);
}
