package com.openclassroom.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.UserDTO;
import com.openclassroom.services.Interfaces.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
    private IUserService userService;
	
	@Operation(summary = "Affichage d'un profil utilisateur", description = "Affichage d'un profil utilisateur depuis son id...")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affichage du profil utilisateur",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@GetMapping("{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
	}
}
