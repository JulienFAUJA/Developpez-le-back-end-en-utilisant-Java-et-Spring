package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openclassroom.dto.RentalDTO;
import com.openclassroom.dto.RentalFormDTO;
import com.openclassroom.dto.RentalResponseDTO;
import com.openclassroom.dto.RentalsResponseDTO;
import com.openclassroom.models.RentalModel;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.RentalRepository;
import com.openclassroom.services.Interfaces.IRentalService;

@Service
public class RentalService implements IRentalService{
	
	@Autowired
	private RentalRepository rentalRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private FileService fileService;
	
	
    
    
   
	@Override 
	public RentalsResponseDTO getRentals(){
		List<RentalFormDTO> rentals = new ArrayList<>();
		this.rentalRepository.findAll().forEach(r -> rentals.add(modelMapper.map(r, RentalFormDTO.class))); 
		RentalsResponseDTO rentalsResponseDTO = new RentalsResponseDTO(rentals);
		return rentalsResponseDTO;
	}
	
	@Override 
	public RentalFormDTO getRentalById(Integer id){
		Optional<RentalModel> rental = this.rentalRepository.findById(id);
		return modelMapper.map(rental.orElseThrow(), RentalFormDTO.class);
	}
	
	@Override 
	public RentalResponseDTO postRental(MultipartFile picture, RentalDTO rentalDTO) {
		RentalModel rental = modelMapper.map(rentalDTO, RentalModel.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel currentUser = (UserModel) authentication.getPrincipal();
        Integer currentUserId = currentUser.getId();
		rental.CreateNow();
		rental.setOwner_id(currentUserId);
		String altPhotoText="Photo(s) non disponible(s)...";
		// Mise à jour de l'URL du fichier dans l'objet RentalModel
		if (picture != null && !picture.isEmpty()) {
			// Enregistrement du fichier
		    String fileUrl;
			try {
				fileUrl = fileService.save(picture);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fileUrl=altPhotoText;
			}
			rental.setPicture(fileUrl);
		} else {
			rental.setPicture(altPhotoText);
		}
		try {
			rentalRepository.save(rental);
		}
		catch(Exception ex) {
		}   
		RentalResponseDTO rentalResponseDTO= new RentalResponseDTO("Annonce postée avec succès...");
		return rentalResponseDTO;
	}
	
	@Override 
	public RentalResponseDTO updateRental(Integer id, RentalFormDTO rentalFormDTO) {
		RentalModel rental = modelMapper.map(rentalFormDTO, RentalModel.class);
		Optional<RentalModel> rental_by_id = rentalRepository.findById(id);
		if(rental_by_id != null) {
			rental.setPicture(rental_by_id.get().getPicture());
			rental.setCreated_at(rental_by_id.get().getCreated_at());
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel currentUser = (UserModel) authentication.getPrincipal();
        Integer currentUserId = currentUser.getId();
		rental.UpdateNow();
		rental.setOwner_id(currentUserId);
		
		try {
			rentalRepository.save(rental);
		}
		catch(Exception ex) {
		}
		RentalResponseDTO rentalResponseDTO= new RentalResponseDTO("Annonce modifiée avec succès...");
		return rentalResponseDTO;
		
        		
	}

	

}
