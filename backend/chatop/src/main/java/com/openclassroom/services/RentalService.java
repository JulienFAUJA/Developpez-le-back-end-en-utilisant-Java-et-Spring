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
import com.openclassroom.models.RentalModel;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.MessageRepository;
import com.openclassroom.repositories.RentalRepository;
import com.openclassroom.services.Interfaces.IAuthService;
import com.openclassroom.services.Interfaces.IRentalService;

@Service
public class RentalService implements IRentalService{
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
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
    
   
	
	public List<RentalFormDTO> getRentals(){
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
	
	public String postRental(MultipartFile picture, RentalDTO rentalDTO) {
		
		RentalModel rental = modelMapper.map(rentalDTO, RentalModel.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel currentUser = (UserModel) authentication.getPrincipal();
        Integer currentUserId = currentUser.getId();
		rental.CreateNow();
		rental.setOwner_id(currentUserId);
		//rental.setUser(currentUser);
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
//		String StatusMessage="Annonce postée avec succès..."; 
//		boolean isAlreadyPresent = rentalRepository.findById(rentalDTO.getId()) != null;
//		System.out.println("rentalDTO:"+isAlreadyPresent != null ? "[UPDATE]" : "[POST]"+rentalDTO.toString());
//		try {
//			rentalRepository.save(rental);
//			StatusMessage=isAlreadyPresent ? "Annonce mise à jour avec succès..." : "Annonce postée avec succès...";
//		}
//		catch(Exception ex) {
//			StatusMessage=isAlreadyPresent ? "Erreur de mise à jour de l'annonce..." : "Erreur de publication de l'annonce...";
//		}
		return "";
		
        		
	}
	
	public String updateRental(Integer id, RentalDTO rentalDTO) {
		
		RentalModel rental = modelMapper.map(rentalDTO, RentalModel.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel currentUser = (UserModel) authentication.getPrincipal();
        Integer currentUserId = currentUser.getId();
		rental.CreateNow();
		rental.setOwner_id(currentUserId);
		//rental.setUser(currentUser);
		
		try {
			rentalRepository.save(rental);
		}
		catch(Exception ex) {
		}
//		String StatusMessage="Annonce postée avec succès..."; 
//		boolean isAlreadyPresent = rentalRepository.findById(rentalDTO.getId()) != null;
//		System.out.println("rentalDTO:"+isAlreadyPresent != null ? "[UPDATE]" : "[POST]"+rentalDTO.toString());
//		try {
//			rentalRepository.save(rental);
//			StatusMessage=isAlreadyPresent ? "Annonce mise à jour avec succès..." : "Annonce postée avec succès...";
//		}
//		catch(Exception ex) {
//			StatusMessage=isAlreadyPresent ? "Erreur de mise à jour de l'annonce..." : "Erreur de publication de l'annonce...";
//		}
		return "";
		
        		
	}

}
