package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.CustomProperties;

@RestController
public class MessageController {	
	
	@PostMapping("api/messages")
	public String postMessage() {
		return "Welcome, User";
	}
		
}
