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
import com.openclassroom.models.MessageModel;
import com.openclassroom.models.RentalModel;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.MessageRepository;
import com.openclassroom.repositories.RentalRepository;
import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.Interfaces.IMessageService;
import com.openclassroom.services.Interfaces.IRentalService;

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
	
	
    
    private MessageRequestDTO convertToDTO(MessageModel message) {
        return modelMapper.map(message, MessageRequestDTO.class);
    }
    
    
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
   
	
//	public Iterable<MessageRequestDTO> getMessages(){
//		List<MessageRequestDTO> messages = new ArrayList<>();
//		this.messageRepository.findAll().forEach(m -> messages.add(this.convertToDTO(m)));
//		return messages;
//	}
//	
//	public MessageRequestDTO getMessageById(Integer id){
//		Optional<MessageModel> message = this.messageRepository.findById(id);
//		System.out.println("MessageService -> message.orElseThrow():"+message.orElseThrow());
//		System.out.println("MessageService -> convertToDTO(message.orElseThrow()):"+convertToDTO(message.orElseThrow()));
//		return convertToDTO(message.orElseThrow());
//	}
	

}
