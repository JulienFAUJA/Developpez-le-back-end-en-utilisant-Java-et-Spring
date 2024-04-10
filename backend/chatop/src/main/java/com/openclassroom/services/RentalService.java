package com.openclassroom.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.models.RentalModel;
import org.springframework.web.multipart.MultipartFile;
import com.openclassroom.repositories.RentalRepository;

@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private FileService fileService;
	
	
    private RentalModel convertToEntity(RentalDTO rentalDto) {
        return modelMapper.map(rentalDto, RentalModel.class);
    }
    
    private RentalDTO convertToDTO(RentalModel rental) {
        return modelMapper.map(rental, RentalDTO.class);
    }
    
   
	
	public Iterable<RentalDTO> getRentals(){
		List<RentalDTO> rentals = new ArrayList<>();
		this.rentalRepository.findAll().forEach(r -> rentals.add(this.convertToDTO(r)));
		return rentals;
	}
	
	public RentalDTO getRentalById(Integer id){
		Optional<RentalModel> rental = this.rentalRepository.findById(id);
		System.out.println("RentalService -> rental.orElseThrow():"+rental.orElseThrow());
		System.out.println("RentalService -> convertToDTO(rental.orElseThrow()):"+convertToDTO(rental.orElseThrow()));
		return convertToDTO(rental.orElseThrow());
	}
	
	public String postRental(RentalDTO rentalDto) throws IOException {
		System.out.println("rentalDto:"+rentalDto.toString());
		RentalModel rental = modelMapper.map(rentalDto, RentalModel.class);
		try {
			if (rentalDto.getPicture() != null && !rentalDto.getPicture().isEmpty()) {
	            String fileUrl;
				fileUrl = fileService.save(rentalDto.getPicture());
				 rental.setPicture(fileUrl);
	        } else {
	        	rental.setPicture("noPicture");
	        }
		} 
		catch (IOException e) {
			e.printStackTrace();
			rental.setPicture("noPicture");
			return "";
		}
        rentalRepository.save(rental);
		return "Annonce postée avec succès..."; 		
	}

}
