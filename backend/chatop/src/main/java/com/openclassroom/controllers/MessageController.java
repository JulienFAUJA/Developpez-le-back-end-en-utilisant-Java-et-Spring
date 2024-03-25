package com.openclassroom.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@PostMapping("/messages")
	public String postMessage() {
		return "Welcome, User";
	}
		
}
