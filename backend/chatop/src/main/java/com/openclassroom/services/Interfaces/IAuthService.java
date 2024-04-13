package com.openclassroom.services.Interfaces;

import org.springframework.http.ResponseEntity;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;

public interface IAuthService {
	
	UserModel getCurrentUser(String label);
	
	ResponseEntity<?> me();
	
	ResponseEntity<?> register(UserRegisterDTO request);
	
	ResponseEntity<?> authenticating(UserLoginDTO request);

}
