package com.openclassroom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@PostMapping("api/auth/register")
	public String postRegister() {
		return "Welcome, User";
	}
	
	@PostMapping("api/auth/login")
	public String postLogin() {
		return "Welcome, User";
	}
	
	@GetMapping("api/auth/me")
	public String getMe() {
		return "Welcome, User";
	}
	

}
