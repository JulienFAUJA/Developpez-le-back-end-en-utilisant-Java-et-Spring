package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.dto.UserDTO;
import com.openclassroom.models.MessageModel;
import com.openclassroom.models.RentalModel;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.MessageRepository;
import com.openclassroom.repositories.RentalRepository;
import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.Interfaces.IMessageService;
import com.openclassroom.services.Interfaces.IRentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class MessageService implements IMessageService{
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
    
    @Operation(summary = "Publication d'un message sur une annonce", description = "Publication d'un message sur une annonce...")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication d'un message sur une annonce",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageResponseDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Champs invalide...",
                    content = {@Content(mediaType = "application/json")}),
    		@ApiResponse(responseCode = "401", description = "Non authorisé...",
            content = {@Content(mediaType = "application/json")}),
	})
    public ResponseEntity<?> postMessage(MessageRequestDTO messageDTO) {
    	if (messageDTO.getMessage() == null || messageDTO.getRental_id() == null || messageDTO.getUser_id() == null) {
    		MessageResponseDTO errorResponse = new MessageResponseDTO();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    	}
		MessageModel messageCreated = modelMapper.map(messageDTO, MessageModel.class);
		
		
		messageCreated.setCreated_atNow();
		messageCreated.setUpdated_atNow();
		try {
			messageRepository.save(messageCreated);
			MessageResponseDTO messageResponse = new MessageResponseDTO("Message créé avec succès...");
	    	return ResponseEntity.ok(messageResponse);
		}
		catch (AuthenticationException e) {
	        // Si une exception d'authentification se produit (par exemple, erreur 401), renvoie un ResponseEntity avec un code de statut 401 Unauthorized
	    	MessageResponseDTO errorResponse = new MessageResponseDTO();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    }
		
    }
   
	

}
