package com.openclassroom.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.MessageResponseDTO;
import com.openclassroom.dto.TokenDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;

import com.openclassroom.services.Interfaces.IAuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	


@Autowired
private IAuthService authService;
	

	@Operation(summary = "Création de compte utilisateur", description = "Permet de créer un compte utilisateur.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Body (corps) de la requête non valide...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@PostMapping(value ="/register")
	@ResponseBody
	public ResponseEntity<?> postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		TokenDTO token =  authService.register(userRegisterDTO);
		if (token == null) {
			MessageResponseDTO errorResponse = new MessageResponseDTO("Unauthorized: ");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		else {
			return ResponseEntity.ok(token);
		}
	}
	

	
	@Operation(summary = "Connexion", description = "Permet à un utilisateur de se connecter à son profil avec ses identifiants.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Erreur d'identifiants: Email ou mot de passe non valides...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@PostMapping(value ="/login", consumes={"application/json"})
    public ResponseEntity<?> login(@Valid @RequestBody(required = true) UserLoginDTO userLoginDTO) {
		TokenDTO token = authService.authenticating(userLoginDTO);
		if (token == null) {
			MessageResponseDTO errorResponse = new MessageResponseDTO("Unauthorized: ");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		else {
			return ResponseEntity.ok(token);
		} 
    }
	
	
	@Operation(summary = "Page de profil", description = "Page deu profil de l'utilisateur connecté")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affichage de la page de profil de l'utilisateur",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageResponseDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Non authorisé...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@GetMapping(value ="/me")
	public ResponseEntity<?> getMe(Principal user) {
		return ResponseEntity.status(HttpStatus.OK).body(this.authService.me(user));
		
	}
	
	
	

}
