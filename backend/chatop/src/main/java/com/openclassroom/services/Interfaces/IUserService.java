package com.openclassroom.services.Interfaces;

import org.springframework.http.ResponseEntity;

import com.openclassroom.dto.UserDTO;

public interface IUserService {
	
	ResponseEntity<?> getUserById(Integer id);

}
