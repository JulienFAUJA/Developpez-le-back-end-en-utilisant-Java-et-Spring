package com.openclassroom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.MessageDTO;
import com.openclassroom.models.MessageModel;
import com.openclassroom.services.JWTokenService;
import com.openclassroom.services.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController {	
	
	@Autowired
    private MessageService messageService;
	

	
	
	@Autowired
	private JWTokenService jwtService;
	
	@PostMapping
	@ResponseBody
	public String postMessage(@Valid @RequestBody MessageDTO messageDTO) {
		String messageCreated = messageService.postMessage(messageDTO);
		System.out.print("MessageController:"+messageCreated.toString());
		return messageCreated;
	}
	
	@GetMapping("/all")
	public List<MessageDTO> getAllMessages() {
		List<MessageDTO> messages = new ArrayList<>();
		messageService.getMessages().forEach(m -> messages.add(m));
		System.out.println("MessageController:"+messages);
		return messages;
	}
	
	@GetMapping("{id}")
	public MessageDTO getMessageById(@PathVariable("id") Integer id) {
		System.out.println("MessageController:"+messageService.getMessageById(id).toString());
		return messageService.getMessageById(id);
	}
		
}
