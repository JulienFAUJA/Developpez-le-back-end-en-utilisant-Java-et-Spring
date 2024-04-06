package com.openclassroom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroom.dto.UserLoggedDTO;
import com.openclassroom.dto.UserLoginDTO;
import com.openclassroom.dto.UserRegisterDTO;
import com.openclassroom.models.UserModel;
import com.openclassroom.repositories.UserRepository;

@Service
public class AuthService {
	
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
    
    public UserLoggedDTO me(Authentication principal){
    	System.out.println("principal:"+principal);
    	UserModel userAuthorized = (UserModel) principal.getPrincipal();
        Integer id = userAuthorized.getId();
        UserModel user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserLoggedDTO.class);
    }
    
    
    public String register(UserRegisterDTO request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "User already exist";
        }

        UserModel user = new UserModel(request.getEmail(), request.getName(), passwordEncoder.encode(request.getPassword()));
        
        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return jwt;

    }

    public String authenticate(UserLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserModel user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);


        return jwt;

    }
  
   

}
