package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.services.Interfaces.IMessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController {	
	
	@Autowired
    private IMessageService messageService;

	
	@PostMapping 
	@ResponseBody
	public ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequestDTO messageDTO) {
		MessageResponseDTO messageResponse = messageService.postMessage(messageDTO);
		if (messageResponse.getMessage() == "Bad request: ") {
			
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponse);
		}
		else if (messageResponse.getMessage() == "Unauthorized: ") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageResponse);
		}
		else {
			return ResponseEntity.ok(messageResponse);
		}
		 
	}
	
		
}
