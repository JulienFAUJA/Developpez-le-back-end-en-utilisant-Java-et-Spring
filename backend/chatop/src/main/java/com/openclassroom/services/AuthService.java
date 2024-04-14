package com.openclassroom.services;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.TokenDTO;
import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;
import com.openclassroom.services.Interfaces.IAuthService;

@Service
public class AuthService implements IAuthService{
	
	 @Autowired
	 private UserRepository userRepository;
	
	 @Autowired
	 private ModelMapper modelMapper;
	
	 private final PasswordEncoder passwordEncoder;
	 private final JWTokenService jwtService;
	 private final AuthenticationManager authenticationManager;

	 public AuthService(PasswordEncoder passwordEncoder, JWTokenService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
	 }
    
     public UserLoggedDTO me(Principal user){
    	return modelMapper.map(this.userRepository.findByEmail(user.getName()), UserLoggedDTO.class);
     }
    
     public TokenDTO register(UserRegisterDTO request) {
    	UserModel user = new UserModel(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
    	String jwt = jwtService.generateToken(request.getEmail());
        // vérifie si existe déjà
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
	        return null;
        }
        else {
        	user = userRepository.save(user);
        }
        TokenDTO token = new TokenDTO(jwt);
	    return token;
     }

     public TokenDTO authenticating(UserLoginDTO request) {
    	    	Authentication authentication;
    	    try {
    	    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                );
    	    	authentication = authenticationManager.authenticate(authToken);
    	    }
    	    catch (AuthenticationException e) {
    	        return null;
    	    }
    	    SecurityContextHolder.getContext().setAuthentication(authentication);
    	    UserModel user = (UserModel)authentication.getPrincipal();
    	    String email = user.getUsername();
    	    String jwt = jwtService.generateToken(email);
    	    TokenDTO token = new TokenDTO(jwt);
    	    return token;
     }
}
