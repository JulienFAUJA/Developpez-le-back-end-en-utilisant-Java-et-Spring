package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.MessageRequestDTO;
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
    
    
    public String postMessage(MessageRequestDTO messageDTO) {
		System.out.println("messageDTO:"+messageDTO.toString());
		MessageModel messageCreated = modelMapper.map(messageDTO, MessageModel.class);
		
		UserModel user = userRepository.findById(messageDTO.getUser_id()).orElseThrow();
        RentalModel rental = rentalRepository.findById(messageDTO.getRental_id()).orElseThrow();
//        messageCreated.setUser(user);
//        messageCreated.setRental(rental);
		messageCreated.setCreated_atNow();
		messageCreated.setUpdated_atNow();
		try {
			messageRepository.save(messageCreated);
	    	return "Message créé avec succès...";
		}
		catch(Exception ex) {
			return ex.getMessage();
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
