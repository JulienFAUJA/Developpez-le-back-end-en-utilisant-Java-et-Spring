package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.TokenDTO;
import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.services.AuthService;
import com.openclassroom.services.JWTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	


@Autowired
private AuthService authService;
	
	//private final AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager, JWTokenService jWTokenService) {
		System.out.println("AuthController:constructor..."+authenticationManager.getClass().toString());
	}
	
	@PostMapping(value ="/register")
	@ResponseBody
	public TokenDTO postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		System.out.print("[postRegister] userRegisterDTO:"+userRegisterDTO.toString());
		TokenDTO token = new TokenDTO(authService.register(userRegisterDTO));
		return token;
	}
	

	
	
	@PostMapping(value ="/login", consumes={"application/json"})
    public TokenDTO login(@RequestBody UserLoginDTO userLoginDTO) {
		TokenDTO token = new TokenDTO(authService.authenticating(userLoginDTO));
		return token;
    }
	
	
	@GetMapping(value ="/me")
	public UserLoggedDTO getMe() {
		UserLoggedDTO userLoggedDto = this.authService.me();
		System.out.println("userLoggedDto:retourné dans Angular:"+userLoggedDto.toString());
		return userLoggedDto;
		
	}
	
	
	

}
//