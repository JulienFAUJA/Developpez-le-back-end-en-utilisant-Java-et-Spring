package com.openclassroom.services.Interfaces;

import com.openclassroom.dto.MessageRequestDTO;

public interface IMessageService {
	
	String postMessage(MessageRequestDTO messageDTO);

}
