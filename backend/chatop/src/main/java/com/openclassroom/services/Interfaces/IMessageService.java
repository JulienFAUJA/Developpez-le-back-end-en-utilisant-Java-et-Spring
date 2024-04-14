package com.openclassroom.services.Interfaces;

import com.openclassroom.dto.MessageRequestDTO;
import com.openclassroom.dto.MessageResponseDTO;

public interface IMessageService {
	
	MessageResponseDTO postMessage(MessageRequestDTO messageDTO);

}
