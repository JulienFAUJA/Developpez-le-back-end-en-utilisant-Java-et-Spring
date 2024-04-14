package com.openclassroom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.models.MessageModel;
import com.openclassroom.repositories.MessageRepository;
import com.openclassroom.services.Interfaces.IMessageService;

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
    public MessageResponseDTO postMessage(MessageRequestDTO messageDTO) {
    	if (messageDTO.getMessage() == null || messageDTO.getRental_id() == null || messageDTO.getUser_id() == null) {
    		MessageResponseDTO errorResponse = new MessageResponseDTO("Bad request: ");
	        return errorResponse;
    	}
		MessageModel messageCreated = modelMapper.map(messageDTO, MessageModel.class);
		
		
		messageCreated.setCreated_atNow();
		messageCreated.setUpdated_atNow();
		try {
			messageRepository.save(messageCreated);
			MessageResponseDTO messageResponse = new MessageResponseDTO("Message créé avec succès...");
	    	return messageResponse;
		}
		catch (AuthenticationException e) {
	        // Si une exception d'authentification se produit (par exemple, erreur 401)
	    	MessageResponseDTO errorResponse = new MessageResponseDTO("Unauthorized: ");
	        return errorResponse;
	    }
		
    }
   
	

}
