package com.openclassroom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("api/user/:id")
	public String getUser() {
		return "Welcome, User";
	}
}
