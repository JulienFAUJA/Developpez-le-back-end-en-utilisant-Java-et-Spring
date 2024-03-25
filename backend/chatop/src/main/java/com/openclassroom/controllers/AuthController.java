package com.openclassroom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@PostMapping("/register")
	public String postRegister() {
		return "Welcome, User";
	}
	
	@PostMapping("/login")
	public String postLogin() {
		return "Welcome, User";
	}
	
	@GetMapping("/me")
	public String getMe() {
		return "Welcome, User";
	}
	

}
