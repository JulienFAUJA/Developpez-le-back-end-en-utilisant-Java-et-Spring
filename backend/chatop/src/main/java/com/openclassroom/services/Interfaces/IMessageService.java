package com.openclassroom.services.Interfaces;

import org.springframework.http.ResponseEntity;

import com.openclassroom.dto.MessageRequestDTO;

public interface IMessageService {
	
	ResponseEntity<?> postMessage(MessageRequestDTO messageDTO);

}
