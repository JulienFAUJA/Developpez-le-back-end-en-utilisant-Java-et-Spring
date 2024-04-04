package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.services.JWTService;
import com.openclassroom.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
private JWTService jwtService;

@Autowired
private UserService userService;
	
	public AuthController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	@PostMapping(value ="/register", consumes={"application/json"})
	public ResponseEntity<String> postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		System.out.print("authController1:"+userRegisterDTO.toString());
		UserModel userCreated = userService.registerUser(userRegisterDTO);
		System.out.print("authController2:"+userCreated.toString());
		if (userCreated != null) {
	        return ResponseEntity.ok("User registered successfully: " + userCreated.toString());
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
	    }
	}
	
	@PostMapping(value ="/login")
	public String getToken(Authentication authentication) {
		System.out.print("getToken1:"+authentication);
		String token = jwtService.generateToken(authentication);
		System.out.print("getToken2:"+token);
		return token;
}
	
	@GetMapping("/me")
	public String getMe() {
		return "Welcome, User";
	}
	

}
//