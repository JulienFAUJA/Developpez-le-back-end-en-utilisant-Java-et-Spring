package com.openclassroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.services.AuthService;
import com.openclassroom.services.JWTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	


@Autowired
private AuthService authService;
	
	//private final AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager, JWTokenService jWTokenService) {
		System.out.println("AuthController:constructor..."+authenticationManager.getClass().toString());
	}
	
	@PostMapping(value ="/register")
	@ResponseBody
	public String postRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
		System.out.print("[postRegister] userRegisterDTO:"+userRegisterDTO.toString());
		String token = authService.register(userRegisterDTO);
//		UserModel userCreated = userService.registerUser(userRegisterDTO);
//		System.out.print("authController2:"+userCreated.toString());
//		if (userCreated != null) {
//	        return jWTokenService.provideJwt(userCreated);
//	    } else {
//	        return "";
//	    }
		return token;
	}
	
//	@PostMapping(value ="/login", consumes={"application/json"})
//	public String getToken(@RequestBody UserLoginDTO userLoginDTO) {
//		System.out.print("getToken1:"+userLoginDTO);
//		UserModel userLogged = userService.loginUser(userLoginDTO);
////		String token = jWTokenService.provideJwt(userLogged);
////		System.out.print("getToken2:"+token);
//		return null;
//}
	
	
	
	@PostMapping(value ="/login", consumes={"application/json"})
	@ResponseBody
    public String login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		String token = authService.authenticating(userLoginDTO);
		return token;
    }
	
	
	@GetMapping("/me")
	public UserLoggedDTO getMe(Authentication principal) {
		return this.authService.me(principal);
		
	}
	
	/*
	 * logging.level.org.springframework.security=DEBUG

logging.file.path=C:\Users\Julie\OneDrive\Documents\Ecole\OpenClassRoom\Projets\Projet03\Code\logs
logging.file.name=myapp.log
spring.session.store-type=jdbc
	 * 
	 */
	

}
//