package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.services.MessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController {	
	
	@Autowired
    private MessageService messageService;

	
	@PostMapping 
	@ResponseBody
	public ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequestDTO messageDTO) {
		return messageService.postMessage(messageDTO);
	}
	
		
}
