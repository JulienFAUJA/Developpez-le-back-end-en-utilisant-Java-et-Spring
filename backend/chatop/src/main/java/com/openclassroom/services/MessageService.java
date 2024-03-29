package com.openclassroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.MessageDTO;
import com.openclassroom.models.MessageModel;
import com.openclassroom.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
    private MessageModel convertToEntity(MessageDTO messageDto) {
        return modelMapper.map(messageDto, MessageModel.class);
    }
    
    private MessageDTO convertToDTO(MessageModel message) {
        return modelMapper.map(message, MessageDTO.class);
    }
    
   
	
	public Iterable<MessageDTO> getMessages(){
		List<MessageDTO> messages = new ArrayList<>();
		this.messageRepository.findAll().forEach(m -> messages.add(this.convertToDTO(m)));
		return messages;
	}
	
	public MessageDTO getMessageById(Integer id){
		Optional<MessageModel> message = this.messageRepository.findById(id);
		System.out.println("MessageService -> message.orElseThrow():"+message.orElseThrow());
		System.out.println("MessageService -> convertToDTO(message.orElseThrow()):"+convertToDTO(message.orElseThrow()));
		return convertToDTO(message.orElseThrow());
	}
	

}
