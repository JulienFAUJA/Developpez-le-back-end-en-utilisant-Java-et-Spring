package com.openclassroom.services.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;

public interface IRentalService {

	List<RentalFormDTO> getRentals();
	
	RentalFormDTO getRentalById(Integer id);
	
	String postRental(MultipartFile picture, RentalDTO rentalDTO);
	
	String updateRental(Integer id, RentalFormDTO rentalFormDTO);
}
