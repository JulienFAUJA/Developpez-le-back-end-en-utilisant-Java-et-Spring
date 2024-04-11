package com.openclassroom.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;
import com.openclassroom.models.RentalModel;
import com.openclassroom.models.UserModel;

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
    
   
	
	public Iterable<RentalFormDTO> getRentals(){
		List<RentalFormDTO> rentals = new ArrayList<>();
		this.rentalRepository.findAll().forEach(r -> rentals.add(modelMapper.map(r, RentalFormDTO.class))); //this.convertToDTO(r)
		return rentals;
	}
	
	public RentalFormDTO getRentalById(Integer id){
		Optional<RentalModel> rental = this.rentalRepository.findById(id);
		System.out.println("RentalService -> rental.orElseThrow():"+rental.orElseThrow());
		System.out.println("RentalService -> convertToDTO(rental.orElseThrow()):"+convertToDTO(rental.orElseThrow()));
		return modelMapper.map(rental.orElseThrow(), RentalFormDTO.class);
	}
	
	public String postRental(MultipartFile picture, RentalDTO rentalDTO) throws IOException {
		System.out.println("rentalDTO:"+rentalDTO.toString());
		RentalModel rental = modelMapper.map(rentalDTO, RentalModel.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel currentUser = (UserModel) authentication.getPrincipal();
        Integer currentUserId = currentUser.getId();
		rental.CreateNow();
		rental.setOwner_id(currentUserId);
		String altPhotoText="Photo(s) non disponible(s)...";
		try {
			

            // Mise à jour de l'URL du fichier dans l'objet RentalModel
			if (picture != null && !picture.isEmpty()) {
				// Enregistrement du fichier
	            String fileUrl = fileService.save(picture);
				rental.setPicture(fileUrl);
	        } else {
	        	rental.setPicture(altPhotoText);
	        }
		} 
		catch (IOException e) {
			e.printStackTrace();
			rental.setPicture(altPhotoText);
			return "";
		}
        rentalRepository.save(rental);
		return "Annonce postée avec succès..."; 		
	}

}
