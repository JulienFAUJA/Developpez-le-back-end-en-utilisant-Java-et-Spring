package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
private AuthService authService;
	

//	public AuthController(AuthenticationManager authenticationManager, JWTokenService jWTokenService) {
//		System.out.println("AuthController:constructor..."+authenticationManager.getClass().toString());
//	}
	
	@Operation(summary = "Création de compte utilisateur", description = "Permet de créer un compte utilisateur.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès...",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Body (corps) de la requête non valide...",
                    content = {@Content(mediaType = "application/json")}),
    })
	@PostMapping(value ="/register")
	@ResponseBody
	public TokenDTO postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		System.out.print("[postRegister] userRegisterDTO:"+userRegisterDTO.toString());
		TokenDTO token = new TokenDTO(authService.register(userRegisterDTO));
		return token;
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
    public TokenDTO login(@RequestBody(required = true) UserLoginDTO userLoginDTO) {
		TokenDTO token = new TokenDTO(authService.authenticating(userLoginDTO));
		return token;
    }
	
	@Operation(summary = "Page de profil", description = "Page deu profil de l'utilisateur connecté")
	@GetMapping(value ="/me")
	public UserLoggedDTO getMe() {
		UserLoggedDTO userLoggedDto = this.authService.me();
		System.out.println("userLoggedDto:retourné dans Angular:"+userLoggedDto.toString());
		return userLoggedDto;
		
	}
	
	
	

}
//