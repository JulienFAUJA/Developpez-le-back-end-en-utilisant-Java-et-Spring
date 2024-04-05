package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.services.AuthService;
import com.openclassroom.services.JWTService;
import com.openclassroom.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
private JWTService jwtService;

@Autowired
private UserService userService;

@Autowired
private AuthService authService;
	
	public AuthController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	@PostMapping(value ="/register", consumes={"application/json"})
	public String postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		UserModel userCreated = userService.registerUser(userRegisterDTO);
		System.out.print("authController2:"+userCreated.toString());
		if (userCreated != null) {
	        return jwtService.generateToken(userCreated);//ResponseEntity.ok("User registered successfully: " + userCreated.toString());
	    } else {
	        return "";//ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
	    }
	}
	
	@PostMapping(value ="/login", consumes={"application/json"})
	public String getToken(@Valid @RequestBody UserLoginDTO userLoginDTO) {
		System.out.print("getToken1:"+userLoginDTO);
		UserModel userLogged = userService.loginUser(userLoginDTO);
		String token = jwtService.generateToken(userLogged);
		System.out.print("getToken2:"+token);
		return token;
}
	
	@GetMapping("/me")
	public UserLoggedDTO getMe(Authentication principal) {
		System.out.println(principal.getPrincipal());
		return this.authService.me(principal);
		
	}
	

}
//