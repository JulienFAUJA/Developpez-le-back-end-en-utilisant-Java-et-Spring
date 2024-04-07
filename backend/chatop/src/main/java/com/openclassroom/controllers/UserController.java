package com.openclassroom.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.UserDTO;
import com.openclassroom.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserService userService;
	
//	@GetMapping("/all")
//	public List<UserDTO> getAllUsers() {
//		List<UserDTO> users = new ArrayList<>();
//		userService.getUsers().forEach(u -> users.add(u));
//		System.out.println("UserController:"+users);
//		return users;
//	}
	
	@GetMapping("{id}")
	public UserDTO getUser(@PathVariable("id") Integer id) {
		System.out.println("id:"+id.toString());
		return userService.getUserById(id);
	}
}
